package com.fsd.quvideo.http

import com.fsd.quvideo.model.entity.AppConfig
import com.fsd.quvideo.model.entity.BasicBean
import retrofit2.http.*


interface HttpService {

  companion object {
    const val url = "http://app.bnruevn.com"
  }

  //    //首页
  //    @GET("/article/list/{page}/json")
  //    suspend fun getIndexList(@Path("page") page: Int): BasicBean<ListWrapper<Article>>
  //

  //获取全局配置
  @POST("voi/app/getAppConfigs")
  suspend fun getAppConfigs(@Header("s") s:String , @QueryMap map: Map<String, @JvmSuppressWildcards Any> ): BasicBean<AppConfig>

  //用户未登录情况下获取token deviceId 设备唯一码，保证同设备唯一
  //  @POST("voi/authorization/getDeviceToken")
  //  suspend fun getDeviceToken(@Field()):AppConfigResponse
}
