package com.fsd.quvideo.ui.common

sealed class Destinations(val route:String){
  //首页大框架
  object HomeFrame: Destinations("HomeFrame")
  object Splash: Destinations("Splash")
  object Featured: Destinations("Featured")

}
