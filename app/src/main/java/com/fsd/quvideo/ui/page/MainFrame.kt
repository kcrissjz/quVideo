package com.fsd.quvideo.ui.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.fsd.quvideo.R
import com.fsd.quvideo.model.entity.NavigationItem
import com.fsd.quvideo.ui.theme.primary
import kotlin.system.exitProcess

@Composable
fun MainFrame(navCtrl: NavHostController) {
  val navigationItems = listOf(
    NavigationItem(
      "首页",
      defImages = R.mipmap.ic_home_tab1_normal,
      selectImages = R.mipmap.ic_home_tab1_selected
    ),
    NavigationItem(
      "分类",
      defImages = R.mipmap.ic_home_tab2_normal,
      selectImages = R.mipmap.ic_home_tab2_selected
    ),
    NavigationItem(
      "VIP",
      defImages = R.mipmap.ic_home_tab3_normal,
      selectImages = R.mipmap.ic_home_tab3_selected
    ),
    NavigationItem(
      "我的",
      defImages = R.mipmap.ic_home_tab4_normal,
      selectImages = R.mipmap.ic_home_tab4_selected
    ),
  )
  var currentNavigationIndex by remember {
    mutableStateOf(0)
  }

  BackHandler() {
    exitProcess(0)
  }

  Scaffold(bottomBar = {
    BottomNavigation(
      backgroundColor = when (currentNavigationIndex) {
        0,2 -> Color(0xff000000)
        1,3 -> Color(0xffffffff)
        else -> Color(0xffffffff)
      },
      modifier = Modifier.navigationBarsPadding()
    ) {
      navigationItems.forEachIndexed { index, navigationItem ->
        BottomNavigationItem(
          selected = currentNavigationIndex == index,
          onClick = {
            currentNavigationIndex = index
          },
          icon = {
            Image(
              painter = painterResource(id = if (currentNavigationIndex == index) navigationItem.selectImages else navigationItem.defImages),
              contentDescription = null,
              modifier = Modifier.size(20.dp)
            )
          },
          label = {
            Text(
              text = navigationItem.title,
              color = if (currentNavigationIndex == index) primary else Color.Gray
            )
          },
        )
      }
    }
  }) {
    Box(modifier = Modifier.padding(it)) {
      when (currentNavigationIndex) {
        0 -> HomePage(navCtrl = navCtrl)
        1 -> CategoryPage(navCtrl = navCtrl)
        2 -> VipPage(navCtrl = navCtrl)
        3 -> MinePage(navCtrl = navCtrl)
      }
    }
  }
}



