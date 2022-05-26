package com.fsd.quvideo.ui.navigation


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.fsd.quvideo.ui.page.MainFrame
import com.fsd.quvideo.ui.page.SplashPage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
  val navController = rememberAnimatedNavController()

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


