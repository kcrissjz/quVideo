package com.fsd.quvideo.ui.page.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fsd.quvideo.http.HttpService
import com.fsd.quvideo.http.PageState
import com.fsd.quvideo.http.encrypt.NoneParam
import com.fsd.quvideo.model.entity.CategoryItem
import com.fsd.quvideo.ui.common.TabTitle
import com.fsd.quvideo.util.LogHelper
import com.fsd.quvideo.util.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val service: HttpService) :ViewModel() {
    var viewStates by mutableStateOf(CategoryViewState())
        private set
    init {
        dispatch(CategoryViewAction.FetchData)
    }

    fun dispatch(action: CategoryViewAction) {
        when (action) {
            is CategoryViewAction.FetchData -> {
                fetchData()
            }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            flow {
                val pair = NoneParam.getPair()
                emit(service.getTypePageTypes(pair.first, pair.second))
            }.onStart {
                viewStates = viewStates.copy(pageState = PageState.Loading)
            }.map {
                it.data ?: arrayListOf()
            }.onEach {
                viewStates = viewStates.copy(
                    pageState = PageState.Success(it.isEmpty()),
                    headTypes = it[0].items?: emptyList(),
                )
                it[0].items?.forEachIndexed { index, item ->
                    viewStates.tabTitleList.add(TabTitle(id = item?.mtid?:"", text = item?.name?:""))
                }
            }.catch {
                showToast(it.message)
                LogHelper.d("catch:${it.message}")
                viewStates = viewStates.copy(pageState = PageState.Error(it))
            }. collect()

        }
    }


}
data class CategoryViewState(
    val pageState: PageState = PageState.Loading,
    val headTypes: List<CategoryItem.Item?>? = emptyList(),
    val tabTitleList:MutableList<TabTitle> = arrayListOf()
)

sealed class CategoryViewAction() {
    object FetchData : CategoryViewAction()
}