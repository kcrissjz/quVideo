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
import com.fsd.quvideo.R
import com.fsd.quvideo.ui.components.ShowAgreementDialog
import com.fsd.quvideo.manager.AppConfigManager
import com.fsd.quvideo.viewmodel.SplashViewModel

@Composable
fun SplashPage(onNavigationToMain: () -> Unit = {},splashViewModel: SplashViewModel= hiltViewModel()) {
  val context = LocalContext.current
  LaunchedEffect(Unit) {
    //    delay(3000)
    //    onNavigationToMain()
    splashViewModel.getAppConfigs()
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
        })
    }

  }
}


