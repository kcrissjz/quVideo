package com.fsd.quvideo.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.model.ServiceConfig
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SplashViewModel(context: Context) :ViewModel() {


  val serviceConfig = ServiceConfig(context)
  var isFirstEnter by mutableStateOf(true)
  init {
    viewModelScope.launch {
      isFirstEnter = serviceConfig.isFirstEnter.firstOrNull() == true
    }
  }
  fun saveFirstEnter() {
    viewModelScope.launch {
      serviceConfig.saveFirstEnter(false)
    }
  }
}