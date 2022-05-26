package com.fsd.quvideo.http.interceptor

import android.os.Build
import com.fsd.quvideo.App
import com.fsd.quvideo.BuildConfig
import com.fsd.quvideo.manager.AppUserManager.token
import com.fsd.quvideo.util.DeviceUtils
import com.fsd.quvideo.util.getAppChannelName
import okhttp3.Interceptor
import okhttp3.Response

class ParamIntercaptor : Interceptor {
  private val API_VERSION = "3"
  private val SYSTEM_TYPE = "2"

  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    val newRequest = request.newBuilder()
      .header("apiVersion", API_VERSION)
      .header("appVersion", BuildConfig.VERSION_NAME)
      .header("systemType", SYSTEM_TYPE)
      .header("brand", Build.BRAND)
      .header("model", Build.MODEL)
      .header("deviceId", DeviceUtils.getDeviceUUID(App.context))
      .header("version", "API" + Build.VERSION.SDK_INT)
      .header("storeType", getAppChannelName())
      .header("t", token)
      .method(request.method, request.body)
      .build()
    return chain.proceed(newRequest)
  }
}