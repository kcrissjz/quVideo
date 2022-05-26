package com.fsd.quvideo.http.encrypt;

import androidx.core.util.Pair;

import com.fsd.quvideo.http.encrypt.JSONReqParams;

import java.util.Map;

public class NoneParam {
    public static Pair<String, Map<String, Object>> getPair1() {
        JSONReqParams reqParams = JSONReqParams.construct()
                .put("timestamp", System.currentTimeMillis());
        return new Pair<>(reqParams.encrypt(), reqParams.getMap());
    }
}
