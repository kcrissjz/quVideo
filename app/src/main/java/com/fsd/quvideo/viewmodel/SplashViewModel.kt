package com.fsd.quvideo.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.http.HttpService
import com.fsd.quvideo.http.encrypt.NoneParam
import com.fsd.quvideo.http.encrypt.Param
import com.fsd.quvideo.util.LogHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private var service: HttpService,) :ViewModel() {

  fun getAppConfigs(){
    viewModelScope.launch {
       flow {
         val pair = Param().getPair()
         LogHelper.d("pair${pair.first}--${pair.second}")
         emit(service.getAppConfigs(pair.first,pair.second))
       }.onStart {
          Log.d("getAppConfigs","onStart:")
       }.onEach {
         Log.d("getAppConfigs","onEach:${it.code}--${it.msg}--${it.data}")

       }.collect()

    }
  }

}