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
data class UserInfo(val userId: String, val nickname: String,val vipLevel:String) : Parcelable