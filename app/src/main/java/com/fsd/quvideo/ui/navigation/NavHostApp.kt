package com.fsd.quvideo.ui.navigation


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
      route = Destinations.Splash.route,
    ) {
      SplashPage(navCtrl = navController)
    }

    composable(
      route = Destinations.HomeFrame.route,
//      route = Destinations.HomeFrame.route + "/{id}"
//      arguments = listOf(navArgument("id"){type = NavType.IntType}

//      route = Destinations.HomeFrame.route + "/{webData}",
//      arguments = listOf(navArgument("webData") { type = NavType.StringType}

      enterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
      },
      exitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
      }
    ) {
//      val args = it.arguments?.getString("webData")?.fromJson<WebData>()
//      if (args != null) {
//        MainFrame(webData = args, navCtrl = navCtrl)
//      }
      MainFrame(navController)
    }


  }
}


