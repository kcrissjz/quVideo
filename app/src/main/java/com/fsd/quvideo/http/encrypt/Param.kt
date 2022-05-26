package com.fsd.quvideo.http.encrypt

import androidx.core.util.Pair

public class Param {
  fun getPair(): Pair<String, Map<String, Any>> {
    val reqParams = JSONReqParams.construct()
      .put("timestamp", System.currentTimeMillis())
    return Pair(reqParams.encrypt(), reqParams.map)
  }
}