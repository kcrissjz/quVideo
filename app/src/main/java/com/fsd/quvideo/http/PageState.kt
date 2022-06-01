package com.fsd.quvideo.http

sealed class PageState {
  object Loading : PageState()
  object Refreshing : PageState()
  data class Success(val isEmpty: Boolean) : PageState()
  data class Error(val exception: Throwable) : PageState()
}

sealed class FetchStatus {
  object Fetching : FetchStatus()
  object Fetched : FetchStatus()
  object NotFetched : FetchStatus()
}

