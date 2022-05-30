package com.fsd.quvideo.ui.common


import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChangeConsumed
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.model.entity.Banner
import com.fsd.quvideo.ui.theme.*
import com.fsd.quvideo.util.LogHelper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    httpPageState: PageState,
    list: List<Banner>,
    timeMillis: Long = 3000,
    indicatorAlignment: Alignment = Alignment.BottomStart,
    onClick: (type: String, content: String) -> Unit
) {
    val virtualCount = Int.MAX_VALUE
    val actualCount = list.size
    val initialIndex = virtualCount / 2
    val pagerState = rememberPagerState(initialPage = initialIndex)
    val scope = rememberCoroutineScope()


    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

        }, timeMillis, timeMillis)
        onDispose {
            timer.cancel()
        }
    }

    Box(modifier = Modifier) {
        HorizontalPager(
            count = virtualCount,
            state = pagerState,
        ) { index ->
            if (actualCount == 0) {
                return@HorizontalPager
            }
            val currentIndex = index % actualCount
            AsyncImage(
                model = list[currentIndex].url, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(dp_8))
                    .aspectRatio(7 / 3f)
                    .placeholder(
                        visible = httpPageState is PageState.Loading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                contentScale = ContentScale.Crop
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dp_34)
                .align(indicatorAlignment)
                .background(color = Color(0x55000000))
                .padding(horizontal = dp_8)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp, topEnd = 0.dp, bottomStart = dp_8,
                        bottomEnd = dp_8
                    )
                )
        ) {
            LogHelper.d("HorizontalPager-=>pagerState.currentPage${pagerState.currentPage}")
            list.forEachIndexed { index, banner ->
                if (pagerState.currentPage % list.size == index)
                    Text(text = list[pagerState.currentPage % list.size].title, fontSize = sp_15, color = gray_999,
                    modifier = Modifier.weight(1f))
            }
            list.forEachIndexed { index, banner ->

                var dotWidth by remember {
                    mutableStateOf(dp_5)
                }
                var dotHeight by remember {
                    mutableStateOf(dp_5)
                }
                dotWidth = if (pagerState.currentPage % list.size == index) dp_9 else dp_5

                val dotColor = if (pagerState.currentPage % list.size == index) white else gray_999
                Box(
                    modifier = Modifier
                        .background(color = dotColor)
                        .animateContentSize()
                        .width(dotWidth)
                        .height(dotHeight)

                )
                //指示点间的间隔
                if (index != list.lastIndex) Spacer(
                    modifier = Modifier
                        .height(0.dp)
                        .width(4.dp)
                )
            }
        }

    }
}


