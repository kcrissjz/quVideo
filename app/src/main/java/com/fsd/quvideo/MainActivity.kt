package com.fsd.quvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.fsd.quvideo.ui.common.NavHostApp
import com.fsd.quvideo.ui.theme.QuVideoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //让内容显示在状态栏和系统导航栏后面：状态栏和导航栏会遮盖部分内容
    WindowCompat.setDecorFitsSystemWindows(window,false)
    setContent {
//      //实例系统UI控制
//      val systemUiController = rememberSystemUiController()
////      设置为透明状态栏
//      systemUiController.setStatusBarColor(
//        Color.Transparent, darkIcons = MaterialTheme.colors.isLight)
      QuVideoTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          NavHostApp()
        }
      }
    }
  }
}
