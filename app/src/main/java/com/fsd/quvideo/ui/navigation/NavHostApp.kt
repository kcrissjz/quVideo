package com.fsd.quvideo.ui.navigation


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.fsd.quvideo.compositonLocal.LocalSplashViewModel
import com.fsd.quvideo.ui.page.MainFrame
import com.fsd.quvideo.ui.page.SplashPage
import com.fsd.quvideo.viewmodel.SplashViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
  val navController = rememberAnimatedNavController()
  val context = LocalContext.current

  CompositionLocalProvider(LocalSplashViewModel provides SplashViewModel(context) ) {
    AnimatedNavHost(
      navController = navController,
      startDestination = Destinations.Splash.route
    ) {
      composable(
        Destinations.Splash.route,
        enterTransition = {
          slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
        },
        exitTransition = {
          slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
        }
      ) {
        SplashPage(onNavigationToMain = {
          navController.navigate(Destinations.HomeFrame.route)
        })
      }

      composable(
        Destinations.HomeFrame.route,
        enterTransition = {
          slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
        },
        exitTransition = {
          slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
        }
      ) {
        MainFrame()
      }


    }
  }
}


