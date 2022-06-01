package com.fsd.quvideo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.http.HttpService
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.http.encrypt.JSONReqParams
import com.fsd.quvideo.http.encrypt.NoneParam
import com.fsd.quvideo.model.entity.FeatureData
import com.fsd.quvideo.model.entity.HeadMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val service: HttpService) : ViewModel() {
    var viewStates by mutableStateOf(HomeViewState())
        private set

    init {
        dispatch(HomeViewAction.FetchData)
    }

    fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.FetchData -> {
                fetchData()
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            flow {
                val pair = NoneParam.getPair()
                emit(service.getHeaderMenus(pair.first, pair.second))
            }.onStart {
                viewStates = viewStates.copy(pageState = PageState.Loading)
            }.map {
                it.data ?: arrayListOf()
            }.onEach {
                viewStates = viewStates.copy(
                    pageState = PageState.Success(it.isEmpty()),
                    headMenus = it
                )
            }.catch {
                viewStates = viewStates.copy(pageState = PageState.Error(it))
            }. collect()

        }
    }



}

data class HomeViewState(
    val pageState: PageState = PageState.Loading,
    val headMenus: MutableList<HeadMenu> = arrayListOf()
)

sealed class HomeViewAction() {
    object FetchData : HomeViewAction()
}