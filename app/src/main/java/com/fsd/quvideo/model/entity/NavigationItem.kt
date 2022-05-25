package com.fsd.quvideo.model.entity

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 导航栏对象
 */
data class NavigationItem(
  var title: String,
  @DrawableRes var defImages:  Int,
  @DrawableRes var selectImages:  Int,
)