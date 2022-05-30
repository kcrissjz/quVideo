package com.fsd.quvideo.ui.theme

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val ToolBarHeight = 48.sdp()
val TabBarHeight = 48.sdp()
val SearchBarHeight = 32.sdp()
val BottomNavBarHeight = 56.sdp()
val ListTitleHeight = 30.sdp()
val statusBarHeight = 19.sdp()

val PrimaryButtonHeight = 36.sdp()
val MediumButtonHeight = 28.sdp()
val SmallButtonHeight = 28.sdp()


val H1 = 48.ssp()  //超大号标题
val H2 = 36.ssp()  //大号标题
val H3 = 24.ssp()  //主标题
val H4 = 20.ssp()  //普通标题
val H5 = 16.ssp()  //内容文本
val H6 = 14.ssp()  //普通文字尺寸
val H7 = 12.ssp()  //提示语尺寸

val ToolBarTitleSize = 18.ssp()

val cardCorner = 5.sdp()   //卡片的圆角
val buttonCorner = 3.sdp() //按钮的圆角
val buttonHeight = 36.sdp() //按钮的高度


val dp_1 = 1.sdp()
val dp_2 = 2.sdp()
val dp_3 = 3.sdp()
val dp_4 = 4.sdp()
val dp_5 = 5.sdp()
val dp_6 = 6.sdp()
val dp_7 = 7.sdp()
val dp_8 = 8.sdp()
val dp_9 = 9.sdp()
val dp_10= 10.sdp()
val dp_11 = 11.sdp()
val dp_12 = 12.sdp()
val dp_13 = 13.sdp()
val dp_14 = 14.sdp()
val dp_15 = 15.sdp()
val dp_16 = 16.sdp()
val dp_17 = 17.sdp()
val dp_18 = 18.sdp()
val dp_19 = 19.sdp()
val dp_20 = 20.sdp()
val dp_21 = 21.sdp()
val dp_22 = 22.sdp()
val dp_23 = 23.sdp()
val dp_24 = 24.sdp()
val dp_25 = 25.sdp()
val dp_26 = 26.sdp()
val dp_27 = 27.sdp()
val dp_28 = 28.sdp()
val dp_29 = 29.sdp()
val dp_30 = 30.sdp()
val dp_31 = 31.sdp()
val dp_32 = 32.sdp()
val dp_33 = 33.sdp()
val dp_34 = 34.sdp()
val dp_35 = 35.sdp()
val dp_36 = 36.sdp()
val dp_37 = 37.sdp()
val dp_38 = 38.sdp()
val dp_39 = 39.sdp()
val dp_113 = 113.sdp()
val dp_136 = 136.sdp()
val dp_144 = 144.sdp()

val dp_205 = 205.sdp()
val dp_244 = 244.sdp()

val sp_10 = 10.ssp()
val sp_11 = 11.ssp()
val sp_12 = 12.ssp()
val sp_13 = 13.ssp()
val sp_14 = 14.ssp()
val sp_15 = 15.ssp()
val sp_16 = 16.ssp()
val sp_17 = 17.ssp()
val sp_18 = 18.ssp()
val sp_19 = 19.ssp()
val sp_20 = 20.ssp()
val sp_21 = 21.ssp()
val sp_22 = 22.ssp()
val sp_23 = 23.ssp()
val sp_24 = 24.ssp()
val sp_25 = 25.ssp()
val sp_26 = 26.ssp()
val sp_27 = 27.ssp()
val sp_28 = 28.ssp()
val sp_29 = 29.ssp()




fun Int.sdp(): Dp {
    val screenDp =
        Resources.getSystem().displayMetrics.widthPixels / Resources.getSystem().displayMetrics.density
    return (this.toFloat() / 375 * screenDp).dp
}

fun Double.sdp(): Dp {
    val screenDp =
        Resources.getSystem().displayMetrics.widthPixels / Resources.getSystem().displayMetrics.density
    return (this / 375 * screenDp).toInt().dp
}

fun Int.ssp(): TextUnit {
    val screenDp =
        Resources.getSystem().displayMetrics.widthPixels / Resources.getSystem().displayMetrics.density
    return (this.toFloat() / 375 * screenDp).sp
}

fun Double.ssp(): TextUnit {
    val screenDp =
        Resources.getSystem().displayMetrics.widthPixels / Resources.getSystem().displayMetrics.density
    return (this.toFloat() / 375 * screenDp).sp
}

