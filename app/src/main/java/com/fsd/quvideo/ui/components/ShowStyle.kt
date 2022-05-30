package com.fsd.quvideo.ui.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.HotTub
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.fsd.quvideo.R
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.model.entity.Type
import com.fsd.quvideo.model.entity.Video
import com.fsd.quvideo.ui.theme.*
import com.google.accompanist.placeholder.PlaceholderDefaults
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder

@Composable
public fun ShowStyle1() {
    Column {

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ShowStyle4(showStyleData: Type?, pageState: PageState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dp_36)
    ) {
        var moveList = emptyList<Video>()
        if (showStyleData == null) return@Column
        val bagCover = showStyleData.videos[0].cover
        val bagIsCheck = showStyleData.videos[0].isCheck

        if (showStyleData.videos.size > 7) {
            moveList = showStyleData.videos.subList(1, 7)
        } else {
            moveList = showStyleData.videos.subList(1, showStyleData.videos.size)
        }
        ShowStyleTitle(showStyleData.name)
        Box(modifier = Modifier.padding(top = dp_18)) {
            AsyncImage(
                model = bagCover,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .clip(RoundedCornerShape(dp_8))
                    .placeholder(
                        visible = pageState is PageState.Loading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = if (bagIsCheck == "1") R.mipmap.free_icon else R.mipmap.vip_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = dp_4, end = dp_4)
                    .size(width = dp_30, height = dp_13)
                    .align(
                        Alignment.TopEnd
                    ),
            )
        }
        Spacer(modifier = Modifier.height(dp_15))
        ShowStyle4RowImage(moveList[0], moveList[1], moveList[2], pageState = pageState)
        Spacer(modifier = Modifier.height(dp_15))
        ShowStyle4RowImage(moveList[3], moveList[4], moveList[5], pageState = pageState)
    }
}

@Composable
fun ShowStyleTitle(name:String) {
    val myId = "inlineContent"
    val text = buildAnnotatedString {
        appendInlineContent(myId, "[icon]")
        append(" ")
        append(name)
    }
    val inlineContent = mapOf(
        Pair(
            myId,
            InlineTextContent(
                Placeholder(
                    width = sp_14,
                    height = sp_18,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.ic_home_popular),
                    contentDescription = null,
                )
            }
        )
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            inlineContent = inlineContent,
            fontWeight = FontWeight.Bold,
            fontSize = sp_17,
            color = white,
            modifier = Modifier.weight(1f)
        )
        Text(text = "查看更多 >", fontSize = sp_12, color = gray_999)
    }


}
@Composable
fun ShowStyle4RowImage(move1: Video, move2: Video, move3: Video, pageState: PageState) {
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShowStyleRowImageItem(move = move1, pageState = pageState)
        ShowStyleRowImageItem(move = move2, pageState = pageState)
        ShowStyleRowImageItem(move = move3, pageState = pageState)
    }
}
@Composable
fun ShowStyleRowImageItem(move: Video, pageState: PageState){
    Column(modifier = Modifier) {
        Box(modifier = Modifier) {
            AsyncImage(
                model = move.cover,
                contentDescription = null,
                modifier = Modifier
                    .size(width = dp_113, height = dp_144)
                    .clip(RoundedCornerShape(dp_8))
                    .placeholder(
                        visible = pageState is PageState.Loading,
                        highlight = PlaceholderHighlight.shimmer()
                    ),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = if (move.isCheck == "1") R.mipmap.free_icon else R.mipmap.vip_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = dp_4, end = dp_4)
                    .size(width = dp_30, height = dp_13)
                    .align(
                        Alignment.TopEnd
                    ),
            )
        }
        Spacer(modifier = Modifier.height(dp_10))
        Text(text = move.name, fontSize = sp_13, color = white)
    }
}

@Composable
public fun ShowStyle5() {
    Column {

    }
}
