package com.fsd.quvideo.viewmodel


import android.content.Context
import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.App
import com.fsd.quvideo.http.FetchStatus
import com.fsd.quvideo.http.HttpService
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.http.encrypt.JSONReqParams
import com.fsd.quvideo.http.encrypt.NoneParam
import com.fsd.quvideo.http.interceptor.ParamIntercaptor
import com.fsd.quvideo.manager.AppConfigManager
import com.fsd.quvideo.manager.AppUserManager.token
import com.fsd.quvideo.manager.AppUserManager.userInfo
import com.fsd.quvideo.manager.DataStoreManager
import com.fsd.quvideo.model.entity.AppConfig
import com.fsd.quvideo.util.DeviceUtils
import com.fsd.quvideo.util.LogHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private var service: HttpService,) :ViewModel() {

  var viewStates by mutableStateOf(SplashViewState())
    private set


  fun dispatch(action: SplashViewAction){
    when(action){
      SplashViewAction.FetchConfigData ->{getAppConfigs()}
      SplashViewAction.FetchDeviceTokenData ->{getDeviceToken()}
      SplashViewAction.FetchUserInfoData ->{getMyUserDetail()}
    }
  }

  private fun getAppConfigs(){
    viewModelScope.launch {
       flow {
         val pair = NoneParam.getPair()
         emit(service.getAppConfigs(pair.first,pair.second))
       }.onStart {
         viewStates = viewStates.copy(pageState = PageState.Loading)
       }.onEach {
         viewStates = viewStates.copy(
           pageState = PageState.Success(it.data!=null)
         )
         AppConfigManager.appConfig = it.data
       }.catch {
         viewStates = viewStates.copy(pageState = PageState.Error(it))
       }.onCompletion {
          if (token.isNotEmpty()){
            dispatch(SplashViewAction.FetchUserInfoData)
          }else{
            dispatch(SplashViewAction.FetchDeviceTokenData)
          }
       }.collect()

    }
  }

  private fun getDeviceToken(){
    viewModelScope.launch {
      flow {
        val params = JSONReqParams.construct()
        params.put("deviceId", DeviceUtils.getDeviceUUID(App.context))
        emit(service.getDeviceToken(params.encrypt(),params.map))
      }.onStart {
        viewStates = viewStates.copy(pageState = PageState.Loading)
      }.onEach {
        viewStates = viewStates.copy(
          pageState = PageState.Success(it.data!=null)
        )
        token = it.data?.t?:""
      }.catch {
        viewStates = viewStates.copy(pageState = PageState.Error(it))
      }.onCompletion {
        viewStates = viewStates.copy(readyData = true)
      }.collect()
    }
  }

  private fun getMyUserDetail(){
    viewModelScope.launch {
      flow {
        val pair = NoneParam.getPair()
        emit(service.getMyUserDetail(pair.first,pair.second))
      }.onStart {
        viewStates = viewStates.copy(pageState = PageState.Loading)
      }.onEach {
        viewStates = viewStates.copy(
          pageState = PageState.Success(it.data!=null)
        )
        userInfo = it.data
      }.onCompletion {
        viewStates = viewStates.copy(readyData = true)
      }.catch {
        viewStates = viewStates.copy(pageState = PageState.Error(it))
      }.collect()
    }
  }




}
data class SplashViewState(
  val pageState: PageState = PageState.Loading,
  val readyData:Boolean = false
)

sealed class SplashViewAction() {
  object FetchConfigData : SplashViewAction()
  object FetchDeviceTokenData : SplashViewAction()
  object FetchUserInfoData : SplashViewAction()
}