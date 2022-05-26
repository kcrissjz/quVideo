package com.fsd.quvideo.ui.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fsd.quvideo.R
import com.fsd.quvideo.ui.components.ShowAgreementDialog
import com.fsd.quvideo.manager.AppConfigManager
import com.fsd.quvideo.ui.components.LcePage
import com.fsd.quvideo.ui.navigation.Destinations
import com.fsd.quvideo.util.RouteUtils
import com.fsd.quvideo.viewmodel.SplashViewAction
import com.fsd.quvideo.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navCtrl: NavHostController, viewModel: SplashViewModel = hiltViewModel()) {
  val context = LocalContext.current

  val viewState = viewModel.viewStates

  LaunchedEffect(Unit){
    if(!AppConfigManager.isFirstEnter){
      viewModel.dispatch(SplashViewAction.FetchConfigData)
    }
  }

  LaunchedEffect(viewModel.viewStates) {

    if (viewState.readyData) {
      delay(1000)
      RouteUtils.navTo(navCtrl,Destinations.HomeFrame.route)
    }
  }


  Box(modifier = Modifier.fillMaxSize()) {
    Image(
      painter = painterResource(id = R.mipmap.img_splash), contentDescription = null,
      modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
    )
    if (AppConfigManager.isFirstEnter) {
      ShowAgreementDialog(
        onCancelDialog = {
          System.exit(0)
        },
        onConfirmDialog = {
          AppConfigManager.isFirstEnter = false
          viewModel.dispatch(SplashViewAction.FetchConfigData)
        })
    }


  }
}


