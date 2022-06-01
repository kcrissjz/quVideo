package com.fsd.quvideo.ui.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.ui.theme.*

/**
 * 通过State进行控制的Loading、Content、Error页面
 *
 * @param pageState 数据State
 * @param onRetry 错误时的点击事件
 * @param content 数据加载成功时应显示的可组合项
 */
@Composable
fun LcePage(
    pageState: PageState,
    onRetry: () -> Unit,
    Refresh:()->Unit= {},
    content: @Composable () -> Unit
) = when (pageState) {
    is PageState.Loading -> PageLoading()
    is PageState.Refreshing -> Refresh()
    is PageState.Error -> ErrorContent(onRetry)
    is PageState.Success -> {
        if (pageState.isEmpty) {
            PageEmpty()
        } else {
            content()
        }
    }
}

@Composable
fun PageLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                color = white,
                modifier = Modifier
                    .size(dp_30)
            )
            Text(
                text = "正在加载中",
                color = white,
                fontSize = sp_13,
                modifier = Modifier
                    .padding(top = dp_8)
            )
        }
    }
}

@Composable
fun PageEmpty() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.stat_notify_error),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "暂无相关内容",
                color= white,
                fontSize = sp_13,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun ErrorContent(retry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(id = R.drawable.stat_notify_error),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "请求出错啦",
                color = white,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp)
            )
            Button(
                onClick = { retry() },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = primary)
            ) {
                Text(text = "重试",color= white)
            }
        }
    }
}
