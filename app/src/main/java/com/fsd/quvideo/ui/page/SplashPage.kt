package com.fsd.quvideo.ui.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.fsd.quvideo.R
import com.fsd.quvideo.compositonLocal.LocalSplashViewModel
import com.fsd.quvideo.ui.components.ShowAgreementDialog

@Composable
fun SplashPage(onNavigationToMain: () -> Unit = {}) {
  val context = LocalContext.current
  val splashViewModel = LocalSplashViewModel.current
  LaunchedEffect(Unit) {
    //    delay(3000)
    //    onNavigationToMain()
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Image(
      painter = painterResource(id = R.mipmap.img_splash), contentDescription = null,
      modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
    )
    if (splashViewModel.isFirstEnter) {
      ShowAgreementDialog(
        isShow = splashViewModel.isFirstEnter,
        onCancelDialog = {
          System.exit(0)
        },
        onConfirmDialog = {
          splashViewModel.saveFirstEnter()
        })
    }

  }
}
//"　感谢您信任并使用%s的产品和服务！当您使用本APP前，请仔细阅读%s和%s 了解我们对您个人信息的处理规则及申请权限的目的，特向您说明如下：\n" +
//                "　1、我们会遵循隐私协议收集、使用信息，但不会仅因同意本协议而采用强制绑定的方式收集信息；\n" +
//                "　2、基于您的明示授权，我们可能会获取您的存储、相机、相册、位置、麦克风等权限，您有权拒绝或取消授权；\n" +
//                "　3、您可以在相关页面访问、修改、删除您的个人信息或者管理您的授权，我们也提供账号注销的渠道。"




