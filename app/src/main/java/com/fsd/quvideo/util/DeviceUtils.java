package com.fsd.quvideo.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

public class DeviceUtils {
    /**
     * 获取保存的uuid，未保存时候重新生成
     * @param context
     * @return
     */
    public static String getDeviceUUID(Context context) {
        String uuid = loadDeviceUUID(context);
        if (TextUtils.isEmpty(uuid)) {
            uuid = createDeviceUUID(context);
            saveDeviceUUID(context, uuid);
        }
        return uuid;
    }

    /**
     * 生成uuid
     *
     * @param context
     * @return
     */
    public static String createDeviceUUID(Context context) {
        String androidId = getAndroidId(context);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                TelephonyManager mngr = (TelephonyManager)
                        context.getSystemService(Context.TELEPHONY_SERVICE);
                androidId= mngr.getDeviceId();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

//        androidId = "9774d56d682e549c";
        //有些手机系统存在bug，androidId被写死为9774d56d682e549c，小概率事件可以忽略
//        if ("9774d56d682e549c".equals(androidId)) {
        //生成随机androidId，app卸载会丢失
//            Random random = new Random();
//            androidId = Integer.toHexString(random.nextInt()) + Integer.toHexString(random.nextInt()) + Integer.toHexString(random.nextInt());
//        }
        return new UUID(androidId.hashCode(), getPhoneInfo().hashCode()).toString();
    }

    public static String getPhoneInfo() {
        //选取一些系统不变参数参与计算uuid
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Build.BRAND).append("/");
        stringBuffer.append(Build.PRODUCT).append("/");
        stringBuffer.append(Build.DEVICE).append("/");
        stringBuffer.append(Build.ID).append("/");
        stringBuffer.append(Build.VERSION.INCREMENTAL);
        return stringBuffer.toString();
    }

    private static void saveDeviceUUID(Context context, String uuid) {
        context.getSharedPreferences("app_device_config", Context.MODE_PRIVATE).edit().putString("uuid", uuid).apply();
    }

    private static String loadDeviceUUID(Context context) {
        return context.getSharedPreferences("app_device_config", Context.MODE_PRIVATE).getString("uuid", null);
    }

    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
