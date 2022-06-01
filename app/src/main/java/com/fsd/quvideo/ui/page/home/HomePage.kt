package com.fsd.quvideo.ui.page.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fsd.quvideo.ui.common.HomeSearchBar
import com.fsd.quvideo.ui.common.TabTitle
import com.fsd.quvideo.ui.common.TextTabBar
import com.fsd.quvideo.ui.theme.black_211
import com.fsd.quvideo.ui.theme.dp_4
import com.fsd.quvideo.ui.theme.statusBarHeight
import com.fsd.quvideo.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomePage(navCtrl: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val scopeState = rememberCoroutineScope()
    //实例系统UI控制
    val systemUiController = rememberSystemUiController()
    //设置为透明状态栏
    systemUiController.setStatusBarColor(
        Color.Transparent, darkIcons = false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black_211)
    ) {

        val pagerState = rememberPagerState(
            initialPage = 0
        )
        Spacer(modifier = Modifier.height(statusBarHeight))
        TextTabBar(
            index = pagerState.currentPage,
            tabTexts = homeViewModel.viewStates.tabTitleList,
            contentAlign = Alignment.CenterStart,
            onTabSelected = { index ->
                scopeState.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )
        Spacer(modifier = Modifier.height(dp_4))
        HomeSearchBar(
            onSearchClick = {
                //                RouteUtils.navTo(navCtrl, RouteName.ARTICLE_SEARCH + "/111")
            }
        )
        HorizontalPager(
            count = homeViewModel.viewStates.headMenus.size,
            state = pagerState
        ) { index ->
            FeaturedPage(homeViewModel.viewStates.headMenus[index].mhid, navCtrl = navCtrl)
        }
    }
}


@Preview
@Composable
fun HomePagePreview() {
    //    HomePage()
}




