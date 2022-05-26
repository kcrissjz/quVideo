package com.fsd.quvideo.http

import com.fsd.quvideo.model.entity.AppConfig
import com.fsd.quvideo.model.entity.BasicBean
import com.fsd.quvideo.model.entity.DeviceToken
import com.fsd.quvideo.model.entity.UserInfo
import retrofit2.http.*


interface HttpService {

  companion object {
    const val url = "http://app.bnruevn.com"
  }


  //获取全局配置
  @POST("voi/app/getAppConfigs")
  suspend fun getAppConfigs(
    @Header("s") s: String,
    @QueryMap map: Map<String, @JvmSuppressWildcards Any>
  ): BasicBean<AppConfig>

  //用户未登录情况下获取token deviceId 设备唯一码，保证同设备唯一
  @POST("voi/authorization/getDeviceToken")
  suspend fun getDeviceToken(
    @Header("s") s: String,
    @QueryMap map: Map<String, @JvmSuppressWildcards Any>
  ): BasicBean<DeviceToken>

  //获取我的用户信息
  @POST("voi/user/getMyUserDetail")
  suspend fun getMyUserDetail(
    @Header("s") s: String,
    @QueryMap map: Map<String, @JvmSuppressWildcards Any>
  ): BasicBean<UserInfo>

  //获取我的用户信息
  @POST("voi/login/log/save")
  suspend fun logSave(
    @Header("s") s: String,
    @QueryMap map: Map<String, @JvmSuppressWildcards Any>
  ): BasicBean<Any>
}
