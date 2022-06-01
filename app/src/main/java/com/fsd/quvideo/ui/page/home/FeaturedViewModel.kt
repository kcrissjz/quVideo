package com.fsd.quvideo.ui.page.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.http.HttpService
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.http.encrypt.JSONReqParams
import com.fsd.quvideo.model.entity.FeatureData
import com.fsd.quvideo.model.entity.Type
import com.fsd.quvideo.model.entity.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.Types
import javax.inject.Inject

@HiltViewModel
class FeaturedViewModel @Inject constructor(private val service: HttpService) : ViewModel() {
    var featuredViewState by mutableStateOf(FeaturedViewState())
        private set

    fun dispatch(action: FeaturedViewAction) {
        when (action) {
            is FeaturedViewAction.UpdatePageInfoByMhid -> {
                fetchPageInfoByMhidData(action.mhid, pageState = PageState.Loading)
            }
            is FeaturedViewAction.RefreshPageInfoByMhid -> {
                fetchPageInfoByMhidData(action.mhid, pageState = PageState.Refreshing)
            }
        }
    }

    private fun fetchPageInfoByMhidData(mhid: String,pageState: PageState) {
        viewModelScope.launch {
            val params = JSONReqParams.construct().put("mhid", mhid)
            flow {
                emit(service.getPageInfoByHeaderMenusId(params.encrypt(), params.map))
            }.onStart {
                featuredViewState = featuredViewState.copy(pageState = pageState)
            }.onEach {
                featuredViewState = featuredViewState.copy(
                    pageState = PageState.Success(it.data==null),
                    featureData = it.data!!
                )
            }.catch {
                featuredViewState = featuredViewState.copy(pageState = PageState.Error(it))
            }.collect()
        }
    }
}

data class FeaturedViewState(
    val pageState: PageState = PageState.Loading,
    val featureData: FeatureData? = null,
)

sealed class FeaturedViewAction() {
    data class UpdatePageInfoByMhid(val mhid: String) : FeaturedViewAction()
    data class RefreshPageInfoByMhid(val mhid: String) : FeaturedViewAction()
}