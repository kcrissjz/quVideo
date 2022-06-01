package com.fsd.quvideo.model.entity

import android.os.Parcelable
import com.google.gson.annotations.JsonAdapter
import kotlinx.parcelize.Parcelize


data class BasicBean<T>(
    var data: T?,
    var code: Int,
    var msg: String
)

data class ListWrapper<T>(
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int,
    var datas: ArrayList<T>
)

@Parcelize
data class AppConfig(
    val vip_play_count: String,
    val invite_pay_earnings: String,
    val h5_invite: String,
    val default_avatar: String,
    val free_play_count: String,
    val buy_price: String,
    val invite_reg_earnings: String,
    val notify_page: String,
    val help_page: String,
    val show_download_url: String,
    val video_free_play_time: String,
    val video_vip_play_time: String,
) : Parcelable

@Parcelize
data class DeviceToken(val t: String) : Parcelable

@Parcelize
data class UserInfo(val userId: String, val nickname: String, val vipLevel: String) : Parcelable

data class HeadMenu(
    val mhid: String,
    val label: String,
    var alias: String = "",
    var sort: String = "",
    var createTime: String="",
    var updateTime: String = "",
    var status: String = "",
    var cachePosition: Int = 0,
    var selected: Boolean = false
    )

data class FeatureData(
    val banners: List<Banner>,
    val types: List<Type>
)

data class Banner(
    val content: String,
    val createTime: Long,
    val mbid: Int,
    val mhid: Int,
    val sort: Int,
    val status: Int,
    val title: String,
    val type: Int,
    val updateTime: Long,
    val url: String
)
data class Type(
    val createTime: Long,
    val location: Any,
    val mhid: Int,
    val mtCode: String,
    val mtid: Int,
    val name: String,
    val showStyle: Int,
    val sort: Int,
    val status: Int,
    val updateTime: Long,
    val videos: List<Video>
)
data class Video(
    val cover: String,
    val isCheck: String,
    val name: String,
    val shortDesc: String,
    val vid: Int
)

//data class Category(val typeLocation:String,val list:List<CategoryType>?)
//data class CategoryType(var mtid:String?=null,val name: String)