package com.fsd.quvideo.http.encrypt;

import android.util.Base64;
import android.util.Log;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

//import org.apache.tomcat.util.codec.binary.Base64;


/**
 *
 */
public class RSAUtils {

//	protected static final Log log = LogFactory.getLog(RSAUtils.class);

    private static String KEY_RSA_TYPE = "RSA";
    private static String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    private static int KEY_SIZE = 1024;// JDK方式RSA加密最大只有1024位

    private static int ENCODE_PART_SIZE = KEY_SIZE / 8;

    public static final String PUBLIC_KEY_NAME = "public";

    public static final String PRIVATE_KEY_NAME = "private";

    /**
     * RSA加密公钥
     **/
    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCurh1daviotlZTdq/PhGFhXfN1ziFmBWRUjgtGhzIPfpHD9ZAi9dhK5Dyc76Fj8G0KYLfkyx82kp/qG5EGkj6sShKj7NsA+MbQs9WwxIeJN6p8vVvjoaIIvnEay/kZM7IIHi8EUmR4EmihdfWLq8l9QUvcp6ToOciTCsmy9fOzSQIDAQAB";

    /**
     * RSA解密私钥
     **/
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIiv3O1w4ZqSpO6ZUdi7iU1Ys8VxXL2O3zSTJ3Oo+sQJ56RP5ld1hEHk5RDWf+BjecoDrdZ/JDKSsqsXBXB0sUuaPUQZi0LkTRlSMO/uQG5o+LPi/rlov/PLjIuDh6bYuOSlCxiuMJddrYWkSCyrt+b78d4wCxaImBsILOMBTDw5AgMBAAECgYAuh9+nnPPQEPfAmcT5CO52vCmGn0644PgvB6E4iF/FeLG4h9KcF09Mt0UbdEnX80U0dggpquKBrNrRz2Z0n+TynXztACYk0/1R4PiSfpPNBjZxk+bf0EePAmR9BawtVETBtFkGmNGHhmdHosTJoUoZZ+Mxo68AlrGyvoeGqsNiOQJBANHeWtiK/QlNtO/THmSf2Je+iQznX3K0P/br7icFZ7abbc5HkWl4kBjGueVQOyLmiXCx5xY8wFmpJ+IaReKrRBcCQQCmu3XUTMB46RBdA5xZzCXNJflsIP5Ppa2FIvlFi7C0WO0hUV4TfbXMusFw75XZ1QeBybCRQABs0xKh80BPJKQvAkEAtt+Nw9Fg6oF239/URRRwdNHHtKHMrzPX5P7y97ejByDrDuuYDxLKZXyNMf+2eBPwwW2UEvF/HuHiaghAy2I3owJAdUr7IVa9MFIFCOo5W5KaJS/Jv5+WSEGtSDPKWHpk3PqSFxY+sEeHVXcsDl79dDjBBzTz2brEkcoLGZMeco68UQJAU4qJ+9kAGezIzuy2jy+xleGhpUa57aqKY7AkiptwyiXQ5EvBGb1iPNJp9e/2T4xsITxKhXF7Pq6qBg/gmGdewA==";

    /**
     * HTTP响应中使用的RSA加密公钥
     **/
    public static final String RES_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMMqHYQq0vwPGWWVI2lXF3Q9wlBZo6TxGCVtNqmjoMW2Ic4YR33Py1MX7UrQPC1fvFdJjukqsVxApwdEYJHzEv/wixmyy3ilxcTaQ14GejLoHRcwqkCUdfUBTSpPWT4JBeImqKZZP4pmjsLIOrf1l3ynschEKYKg+HkpE95nBKCQIDAQAB";

    /**
     * HTTP响应中使用的RSA解密私钥
     **/
    public static final String RES_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIwyodhCrS/A8ZZZUjaVcXdD3CUFmjpPEYJW02qaOgxbYhzhhHfc/LUxftStA8LV+8V0mO6SqxXECnB0RgkfMS//CLGbLLeKXFxNpDXgZ6MugdFzCqQJR19QFNKk9ZPgkF4iaoplk/imaOwsg6t/WXfKexyEQpgqD4eSkT3mcEoJAgMBAAECgYB3hstZFy+UIQlXR3vCMZL1PZO4tJgS0kp5NxE0N5z54Ac73EEV+AAzcqlRJNiqCcmHRKsF62GVQvgre9Yh/MfvfuN8mV5XWlvR6648GS4SLcGd3P3mzbFY0ONkG/EVw6SWJZbTw+yPkNK3qA/6szjhM3fA2g35i4PDmiXyZoLgAQJBANxWB8bQ/5qGXyPwWCKtjSedCWvTgpWvl1MwV5149OmQ6vSLGfJbF8WXIWx9+PsK7/goPxP7neQdvgWz3KTy3AkCQQCi4/Jo1GqfFPyNHc5eE+jL+43pH6Lu7GT3XbDB/EwINItHQEzWXpal0IjOX7mYPT8NhkegNSW4N1eVouMN6X4BAkEAiQPunhIFgOAidcTV6eqmpoWfADBtOgwRPCgJs+Et27jEgwGlRvKim7rZjA/iLqqkg1rzrsd/bgWYzw8EHIbxkQJALuNw1w42Nt7CqBhpXBldDMK3oeCSdTYlBjO4+s4WUNJXOUZgF+EYelih2M4jTZhruwfpPEuwNOvxUUiXFV0eAQJBANn3+guFfU4p1SSeuviEZK5yYlqdHADo+OtXpjXyRO5l1+SUvS+Zbh8Apwns2ckOsSwOzVSZr0ts+KtOmS0KfVY=";


    /**
     * 创建公钥秘钥
     *
     * @return
     */
    public static Map<String, String> createRSAKeys() {
        Map<String, String> keyPairMap = new HashMap<>();// 里面存放公私秘钥的Base64位加密
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA_TYPE);
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // 获取公钥秘钥
            String publicKeyValue = Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.NO_WRAP);
            String privateKeyValue = Base64.encodeToString(keyPair.getPrivate().getEncoded(), Base64.NO_WRAP);

            // 存入公钥秘钥，以便以后获取
            keyPairMap.put(PUBLIC_KEY_NAME, publicKeyValue);
            keyPairMap.put(PRIVATE_KEY_NAME, privateKeyValue);
        } catch (NoSuchAlgorithmException e) {
            Log.e(RSAUtils.class.getSimpleName(), "当前JDK版本没找到RSA加密算法！");
            e.printStackTrace();
        }
        return keyPairMap;
    }

    /**
     * 公钥加密 描述： 1字节 = 8位； 最大加密长度如 1024位私钥时，最大加密长度为 128-11 = 117字节，不管多长数据，加密出来都是 128
     * 字节长度。
     *
     * @param sourceStr
     * @param publicKeyBase64Str
     * @return
     */
    public static String publicKeyEncode(String sourceStr, String publicKeyBase64Str) {
        byte[] publicBytes = Base64.decode(publicKeyBase64Str, Base64.NO_WRAP);
        // 公钥加密
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicBytes);
        List<byte[]> alreadyEncodeListData = new LinkedList<>();

        int maxEncodeSize = ENCODE_PART_SIZE - 11;
        String encodeBase64Result = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] sourceBytes = sourceStr.getBytes("utf-8");
            int sourceLen = sourceBytes.length;
            for (int i = 0; i < sourceLen; i += maxEncodeSize) {
                int curPosition = sourceLen - i;
                int tempLen = curPosition;
                if (curPosition > maxEncodeSize) {
                    tempLen = maxEncodeSize;
                }
                byte[] tempBytes = new byte[tempLen];// 待加密分段数据
                System.arraycopy(sourceBytes, i, tempBytes, 0, tempLen);
                byte[] tempAlreadyEncodeData = cipher.doFinal(tempBytes);
                alreadyEncodeListData.add(tempAlreadyEncodeData);
            }
            int partLen = alreadyEncodeListData.size();// 加密次数

            int allEncodeLen = partLen * ENCODE_PART_SIZE;
            byte[] encodeData = new byte[allEncodeLen];// 存放所有RSA分段加密数据
            for (int i = 0; i < partLen; i++) {
                byte[] tempByteList = alreadyEncodeListData.get(i);
                System.arraycopy(tempByteList, 0, encodeData, i * ENCODE_PART_SIZE, ENCODE_PART_SIZE);
            }
            encodeBase64Result = Base64.encodeToString(encodeData, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeBase64Result;
    }

    /**
     * 私钥解密
     *
     * @param sourceBase64RSA
     * @param privateKeyBase64Str
     */
    public static String privateKeyDecode(String sourceBase64RSA, String privateKeyBase64Str) {
        byte[] privateBytes = Base64.decode(privateKeyBase64Str, Base64.NO_WRAP);
        byte[] encodeSource = Base64.decode(sourceBase64RSA, Base64.NO_WRAP);
        int encodePartLen = encodeSource.length / ENCODE_PART_SIZE;
        List<byte[]> decodeListData = new LinkedList<>();// 所有解密数据
        String decodeStrResult = null;
        // 私钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateBytes);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA_TYPE);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int allDecodeByteLen = 0;// 初始化所有被解密数据长度
            for (int i = 0; i < encodePartLen; i++) {
                byte[] tempEncodedData = new byte[ENCODE_PART_SIZE];
                System.arraycopy(encodeSource, i * ENCODE_PART_SIZE, tempEncodedData, 0, ENCODE_PART_SIZE);
                byte[] decodePartData = cipher.doFinal(tempEncodedData);
                decodeListData.add(decodePartData);
                allDecodeByteLen += decodePartData.length;
            }
            byte[] decodeResultBytes = new byte[allDecodeByteLen];
            for (int i = 0, curPosition = 0; i < encodePartLen; i++) {
                byte[] tempSorceBytes = decodeListData.get(i);
                int tempSourceBytesLen = tempSorceBytes.length;
                System.arraycopy(tempSorceBytes, 0, decodeResultBytes, curPosition, tempSourceBytesLen);
                curPosition += tempSourceBytesLen;
            }
            decodeStrResult = new String(decodeResultBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodeStrResult;
    }

//	public static void main(String[] args) {
//		String str = "将要加密的字符串";
//		Map<String,String> map = RSAUtils.createRSAKeys();
//		String publicKey = map.get(PUBLIC_KEY_NAME);
//		String privateKey = map.get(PRIVATE_KEY_NAME);
//		System.out.println("公钥:" + publicKey);
//		System.out.println("私钥:" + privateKey);
//		String eStr = RSAUtils.publicKeyEncode(str, publicKey);
//		System.out.println(eStr);
//		String dStr = RSAUtils.privateKeyDecode(eStr, privateKey);
//		System.out.println(dStr);
//	}

    public static void main(String[] args) {

        String original = "{\"mobile\":\"17755554444\"}";
        String encrypted = RSAUtils.publicKeyEncode(original, RSAUtils.PUBLIC_KEY);
        String decrypted = RSAUtils.privateKeyDecode(encrypted, RSAUtils.PRIVATE_KEY);

//        JSONObject json = new JSONObject();
//        try {
//            json.put("mobile", "13851512191");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String jsonStr = json.toString();
//        String estr = RSAUtils.publicKeyEncode(jsonStr, RSAUtils.PUBLIC_KEY);
//        System.out.println(estr);
//        System.out.println(RSAUtils.privateKeyDecode(estr, RSAUtils.PRIVATE_KEY));
    }
}