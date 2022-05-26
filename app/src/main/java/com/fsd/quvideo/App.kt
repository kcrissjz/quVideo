package com.fsd.quvideo

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.fsd.quvideo.manager.DataStoreManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application() {
  companion object{
    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    @SuppressLint("StaticFieldLeak")
    lateinit var instance:App
  }
  override fun onCreate() {
    super.onCreate()
    context = applicationContext
    instance = this
    DataStoreManager.init(this)

  }
}