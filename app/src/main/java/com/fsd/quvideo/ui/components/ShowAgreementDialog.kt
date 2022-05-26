package com.fsd.quvideo.ui.components


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fsd.quvideo.ui.theme.black_333
import com.fsd.quvideo.ui.theme.white
import com.fsd.quvideo.ui.theme.yellow_02d

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShowAgreementDialog(
  title: String = "提示",
  onCancelDialog: () -> Unit = {},
  onConfirmDialog: () -> Unit = {}
) {
  var dialogWidth:Float
  with(LocalDensity.current){
    dialogWidth = ((LocalConfiguration.current.screenWidthDp* 0.25).dp ).toPx()
  }
  var showDialog by remember {
    mutableStateOf(true)
  }
    if (showDialog) {
      Dialog(
        onDismissRequest = { showDialog = false },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
      ) {
        Box(
          modifier = Modifier
            .width(dialogWidth.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = white)
            .padding(horizontal = 16.dp)
        ) {
          Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .padding(vertical = 8.dp)
              .fillMaxWidth()
          ) {
            Text(title, fontSize = 17.sp, color = black_333, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            TextSample()
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
              OutlinedButton(
                onClick = {
                  onCancelDialog()
                  showDialog = false
                },
                border = BorderStroke(1.dp, color = black_333),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
              ) {
                Text(text = "退出", fontSize = 15.sp, color = black_333)
              }
              Spacer(modifier = Modifier.width(8.dp))
              Button(
                onClick = {
                  showDialog = false
                  onConfirmDialog()
                },
                colors = ButtonDefaults.buttonColors(
                  backgroundColor = yellow_02d,
                  contentColor = black_333
                ),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
              ) {
                Text(text = "确认", fontSize = 15.sp)
              }
            }
          }
        }
      }
    }
}


@Composable
fun TextSample() {
  val annotatedString = buildAnnotatedString {
    append("　感谢您信任并使用趣视频的产品和服务！当您使用本APP前，请仔细阅读")

    //往字符串中添加一个注解，直到遇到 pop() 。tag 为注解标识，annotation 为传递内容
    pushStringAnnotation("protocol", annotation = "https://docs.bughub.icu/compose")
    withStyle(style = SpanStyle(yellow_02d)) {
      append("《用户协议》")
    }
    pop()

    append("和")

    pushStringAnnotation("privacy", annotation = "https://randywei.gitee.com")
    withStyle(style = SpanStyle(yellow_02d)) {
      append("《隐私政策》")
    }
    pop()
    append("了解我们对您个人信息的处理规则及申请权限的目的，特向您说明如下：\n　1、我们会遵循隐私协议收集、使用信息，但不会仅因同意本协议而采用强制绑定的方式收集信息；\n　2、基于您的明示授权，我们可能会获取您的存储、相机、相册、位置、麦克风等权限，您有权拒绝或取消授权；\n　3、您可以在相关页面访问、修改、删除您的个人信息或者管理您的授权，我们也提供账号注销的渠道。")
  }

  ClickableText(
    annotatedString, onClick = { offset ->
      //从字符串中查找注解
      annotatedString.getStringAnnotations("protocol", start = offset, end = offset)
        .firstOrNull()?.let { annotation ->
          Log.d("TextSample", "点击了用户协议：${annotation.item}")
        }

      annotatedString.getStringAnnotations("privacy", start = offset, end = offset)
        .firstOrNull()?.let { annotation ->
          Log.d("TextSample", "点击了隐私政策：${annotation.item}")
        }
    }
  )
}

