package com.fsd.quvideo.manager

import com.fsd.quvideo.model.entity.UserInfo
import com.fsd.quvideo.util.fromJson
import com.fsd.quvideo.util.toJson


object AppUserManager {
  private const val LOGGED_FLAG = "logged_flag"
  private const val TOKEN_FLAG = "TOKEN_FLAG"
  private const val USER_INFO = "user_info"
  var isLogged: Boolean
    get() = DataStoreManager.readBooleanData(LOGGED_FLAG, false)
    set(value) = DataStoreManager.saveSyncBooleanData(LOGGED_FLAG, value = value)
  var token: String
    get() = DataStoreManager.readStringData(TOKEN_FLAG, "")
    set(value) = DataStoreManager.saveSyncStringData(TOKEN_FLAG, value = value)

      var userInfo: UserInfo?
          get() = DataStoreManager.readStringData(USER_INFO).fromJson()
          set(value) = DataStoreManager.saveSyncStringData(USER_INFO, value = value?.toJson() ?: "")

  //    fun onLogin(userInfo: UserInfo) {
  //        isLogged = true
  //        AppUserUtil.userInfo = userInfo
  //    }

  //    fun onLogOut() {
  //        isLogged = false
  //        userInfo = null
  //    }
}