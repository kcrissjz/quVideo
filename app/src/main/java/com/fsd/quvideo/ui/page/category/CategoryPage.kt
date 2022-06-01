package com.fsd.quvideo.ui.page.category


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fsd.quvideo.ui.common.HomeSearchBar
import com.fsd.quvideo.ui.common.TextTabBar
import com.fsd.quvideo.ui.page.home.FeaturedPage
import com.fsd.quvideo.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CategoryPage(
    navCtrl: NavHostController,
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    val scopeState = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white)
    ) {
        val pagerState = rememberPagerState(
            initialPage = 0
        )
        Spacer(modifier = Modifier.height(statusBarHeight))
        TextTabBar(
            index = pagerState.currentPage,
            tabTexts = categoryViewModel.viewStates.tabTitleList,
            bgColor = white,
            contentAlign = Alignment.CenterStart,
            onTabSelected = { index ->
                scopeState.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )
        Spacer(modifier = Modifier.height(dp_4))

        HorizontalPager(
            count = categoryViewModel.viewStates.tabTitleList.size,
            state = pagerState
        ) { index ->
            //      FeaturedPage(categoryViewModel.viewStates.headMenus[index].mhid, navCtrl = navCtrl)
            CategorySubPage(
                categoryViewModel.viewStates.headTypes?.get(index)?.mtid ?: "",
                navCtrl = navCtrl
            )
        }
    }
}

@Composable
fun CategorySubPage(mtid: String, navCtrl: NavHostController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.Transparent, darkIcons = true)
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = if (mtid.isEmpty()) "xx" else mtid, fontSize = sp_22)
    }
}


