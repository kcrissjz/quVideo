package com.fsd.quvideo.ui.page.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.fsd.quvideo.ui.theme.dp_30
import com.fsd.quvideo.ui.theme.sp_24
import com.fsd.quvideo.ui.theme.white

@Composable
fun FeaturedPage(
    mhid: String?,
    navCtrl: NavHostController
) {
    Column (modifier = Modifier.fillMaxSize()){
        Text(text = mhid?:"xx", fontSize = sp_24, color = white)
    }
}


