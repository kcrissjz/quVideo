package com.fsd.quvideo.ui.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fsd.quvideo.R
import kotlinx.coroutines.delay

@Composable
fun SplashPage(onNavigationToMain:()->Unit={}) {

  LaunchedEffect(Unit){
    delay(3000)
    onNavigationToMain()
  }
  Box(modifier = Modifier.fillMaxSize()) {
    Image(
      painter = painterResource(id = R.mipmap.img_splash), contentDescription = null,
      modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
    )
  }
}

