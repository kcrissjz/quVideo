package com.fsd.quvideo.http.encrypt;

import com.fsd.quvideo.util.LogHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JSONReqParams {

    private HashMap<String, Object> paramMap;

    private JSONReqParams() {
        paramMap = new HashMap<>();
    }

    public static JSONReqParams construct() {
        return new JSONReqParams();
    }

    public JSONReqParams put(String key, Object value) {
        if (value == null) return this;
        paramMap.put(key, value);
        return this;
    }

    public HashMap<String, Object> getMap() {
        return paramMap;
    }

    private JSONObject generateJSONObject(HashMap<String, Object> dataMap) {
        JSONObject params = new JSONObject();
        try {
            if (dataMap != null) {
                Set<Map.Entry<String, Object>> entries = dataMap.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value == null)
                        continue;
                    if (value instanceof HashMap) {
                        params.put(key, generateJSONObject((HashMap<String, Object>) value));
                    } else if (value instanceof List) {
                        params.put(key, new JSONArray(new Gson().toJson(value)));
                    } else if (isPrimitiveType(value)) {
                        params.put(key, value);
                    } else {
                        params.put(key, new JSONObject(new Gson().toJson(value)));
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 判断对象是否为基本数据类型
     *
     * @param object
     * @return
     */
    public static boolean isPrimitiveType(Object object) {
        return object.getClass().isPrimitive()
                || object instanceof String
                || object instanceof Integer
                || object instanceof Double
                || object instanceof Float
                || object instanceof Long
                || object instanceof Boolean
                || object instanceof Byte
                || object instanceof Short;
    }

    public String toJSONString() {
        return generateJSONObject(paramMap).toString();
    }

    public String encrypt() {
        String json = toJSONString();
        return RSAUtils.publicKeyEncode(json, RSAUtils.PUBLIC_KEY);
    }

    public RequestBody buildRequestBody() {
        String json = toJSONString();
        LogHelper.e(json);
        return RequestBody.create(MediaType.parse("application/json"), json);
    }
}
