package com.fsd.quvideo.http

sealed class PageState {
  object Loading : PageState()
  data class Success(val isEmpty: Boolean) : PageState()
  data class Error(val exception: Throwable) : PageState()
}
