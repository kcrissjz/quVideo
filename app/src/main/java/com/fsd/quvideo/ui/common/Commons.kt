package com.fsd.quvideo.ui.common


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fsd.quvideo.model.entity.HeadMenu
import com.fsd.quvideo.ui.theme.*

/**
 * TabLayout
 */
@Composable
fun TextTabBar(
    index: Int,
    tabTexts: List<HeadMenu>,
    modifier: Modifier = Modifier,
    contentAlign: Alignment = Alignment.Center,
    bgColor: Color = black_211,
    contentColor: Color = yellow_63f,
    onTabSelected: ((index: Int) -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(TabBarHeight)
            .background(bgColor)
            .horizontalScroll(state = rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .align(contentAlign)
        ) {
            tabTexts.forEachIndexed { i, tabTitle ->
                Text(
                    text = tabTitle.label,
                    fontSize = if (index == i) sp_22 else sp_15,
                    fontWeight = if (index == i) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = dp_11)
                        .clickable {
                            onTabSelected?.invoke(i)
                        },
                    color = if (index == i) contentColor else gray_999
                )
            }
        }
    }
}

@Composable
fun HomeSearchBar(
    onSearchClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(SearchBarHeight)
            .padding(horizontal = dp_11)
    ) {
        Row(
            modifier = Modifier
                .height(SearchBarHeight)
                .weight(1f)
                .background(
                    color = gray_444,
                    shape = RoundedCornerShape(dp_16)
                )
                .clickable {
                    onSearchClick()
                },
            verticalAlignment = Alignment.CenterVertically

        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(text = "搜索关键词以空格形式隔开", fontSize = 12.sp, color = gray_999)
            }
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "搜索",
                tint = yellow_92d,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        Spacer(modifier = Modifier.width(dp_8))
        Icon(imageVector = Icons.Default.History, contentDescription = null, tint = gray_999 )
    }
}