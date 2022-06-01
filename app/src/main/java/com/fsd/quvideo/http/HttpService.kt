package com.fsd.quvideo.http

import com.fsd.quvideo.model.entity.*
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
    suspend fun save(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>


    //退出登录
    @POST("voi/authorization/logout")
    suspend fun logSave(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    //获取头部菜单列表
    @POST("voi/ment/getHeaderMenus")
    suspend fun getHeaderMenus(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<MutableList<HeadMenu>>

    @POST("voi/ment/getTypePageTypes")
    suspend fun getTypePageTypes(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<List<CategoryItem>>

    @POST("voi/video/getVideosByMtids")
    suspend fun getVideosByMtids(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/getVideosByMtid")
    suspend fun getVideosByMtid(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/ment/getPageInfoByHeaderMenusId")
    suspend fun getPageInfoByHeaderMenusId(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<FeatureData>

    @POST("voi/vip/getBaseVips")
    suspend fun getBaseVips(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    //获取搜索页分类类型列表
    @POST("voi/ment/getSearchTypes")
    suspend fun getSearchTypes(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/search")
    suspend fun search(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/user/getMyPlayHistory")
    suspend fun getMyPlayHistory(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/user/deleteMyPlayHistory")
    suspend fun deleteMyPlayHistory(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/getVideoById")
    suspend fun getVideoById(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/playVideos")
    suspend fun playVideos(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/addVideoPlayRecord")
    suspend fun addVideoPlayRecord(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>

    @POST("voi/video/getRecommendVideos")
    suspend fun getRecommendVideos(
        @Header("s") s: String,
        @QueryMap map: Map<String, @JvmSuppressWildcards Any>
    ): BasicBean<Any>
    //
    //  @POST("voi/video/otherVideos")
    //  fun otherVideos(@Header("s") s: String?, @QueryMap map: Map<String?, Any?>?): Observable<String?>?
    //
    //  @POST("voi/user/getMyPlayInfo")
    //  fun getMyPlayInfo(
    //    @Header("s") s: String?,
    //    @QueryMap map: Map<String?, Any?>?
    //  ): Observable<String?>?
    //
    //  @POST("voi/invite/getMyInviteUrl")
    //  fun getMyInviteUrl(
    //    @Header("s") s: String?,
    //    @QueryMap map: Map<String?, Any?>?
    //  ): Observable<String?>?
    //
    //  @POST("voi/invite/getMyInviteUserList")
    //  fun getMyInviteUserList(
    //    @Header("s") s: String?,
    //    @QueryMap map: Map<String?, Any?>?
    //  ): Observable<String?>?
    //
    //  @POST("voi/app/suggest")
    //  fun suggest(@Header("s") s: String?, @QueryMap map: Map<String?, Any?>?): Observable<String?>?
    //
    //  //获取APP版本升级信息 platform 1.安卓 2.苹果
    //  @POST("voi/app/getAppVersionUpdateInfo")
    //  fun getAppVersionUpdateInfo(
    //    @Header("s") s: String?,
    //    @QueryMap map: Map<String?, Any?>?
    //  ): Observable<String?>?
    //
    //  //获取位置下广告信息 type 1.启动开屏 2.弹出 3.视频 4.个人中心
    //  @POST("voi/adver/getAdverInfo")
    //  fun getAdverInfo(
    //    @Header("s") s: String?,
    //    @QueryMap map: Map<String?, Any?>?
    //  ): Observable<String?>?
}
