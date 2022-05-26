package com.fsd.quvideo.manager

import com.fsd.quvideo.model.entity.AppConfig
import com.fsd.quvideo.util.fromJson
import com.fsd.quvideo.util.toJson


object AppConfigManager {
    private const val ISFIRSTENTER_FLAG = "ISFIRSTENTER_FLAG"
    private const val APPCONFIG_FLAG = "APPCONFIG_FLAG"
    /**
     * 保存第一次打开app用户协议提示框
     */
    var isFirstEnter: Boolean
        get() = DataStoreManager.readBooleanData(ISFIRSTENTER_FLAG, true)
        set(value) = DataStoreManager.saveSyncBooleanData(ISFIRSTENTER_FLAG, value = value)

    var appConfig: AppConfig?
        get() = DataStoreManager.readStringData(APPCONFIG_FLAG).fromJson()
        set(value) = DataStoreManager.saveSyncStringData(APPCONFIG_FLAG, value = value?.toJson() ?: "")


}