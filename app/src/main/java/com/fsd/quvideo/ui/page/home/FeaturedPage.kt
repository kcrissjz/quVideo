package com.fsd.quvideo.ui.page.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fsd.quvideo.ui.common.Banner
import com.fsd.quvideo.ui.components.ShowStyle1
import com.fsd.quvideo.ui.components.ShowStyle4
import com.fsd.quvideo.ui.components.ShowStyle5
import com.fsd.quvideo.ui.theme.dp_11
import com.fsd.quvideo.ui.theme.dp_19

@Composable
fun FeaturedPage(
    mhid: String?,
    navCtrl: NavHostController,
    featuredViewModel: FeaturedViewModel = hiltViewModel()
) {
    val viewState = featuredViewModel.featuredViewState

    LaunchedEffect(Unit) {
        featuredViewModel.dispatch(FeaturedViewAction.UpdatePageInfoByMhid(mhid = mhid!!))
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = dp_11, vertical = dp_19)
            .fillMaxSize()
    ) {
        item {
            Banner(
                viewState.pageState,
                list = featuredViewModel.featuredViewState.featureData?.banners ?: emptyList(),
                onClick = { type, content ->

                })
        }
        item {
            viewState.featureData?.types?.forEachIndexed { index, type ->
                when(viewState.featureData.types[index].showStyle){
                    4->{
                        if (viewState.showStyle4Data!=null){
                            ShowStyle4(showStyleData = viewState.featureData.types[index], pageState  = viewState.pageState)
                        }
                    }
                }
            }

        }
    }
}




