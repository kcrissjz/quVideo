package com.fsd.quvideo.ui.page


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomePage() {
    Column() {
        Text(text = "首页")
    }
}

@Preview
@Composable
fun HomePagePreview() {
  HomePage()
}

