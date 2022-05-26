package com.fsd.quvideo.util

import android.content.pm.PackageManager
import com.fsd.quvideo.App

fun getAppChannelName(): String {
  return getMetadataString("APP_CHANNEL")
}
fun getMetadataString(key: String): String {
  return App.context.packageManager.getApplicationInfo(
    App.context.packageName, PackageManager.GET_META_DATA
  ).metaData.getString(key) ?:"none"
}