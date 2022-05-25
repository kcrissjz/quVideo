package com.fsd.quvideo.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServiceConfig(private val context: Context) {
  companion object {
    private val Context.userStore: DataStore<Preferences> by preferencesDataStore("service_store")
    val ISFIRSTENTER = booleanPreferencesKey("IS_FIRSTENTER")

  }

  val isFirstEnter: Flow<Boolean> = context.userStore.data.map {
    it[ISFIRSTENTER] ?: true
  }

  /**
   * 第一次进入app 显示隐私协议弹框
   */
  suspend fun saveFirstEnter(isFirstEnter: Boolean) {
    context.userStore.edit {
      it[ISFIRSTENTER] = isFirstEnter
    }
  }

  suspend fun clear(){
    context.userStore.edit {
//      it[ISFIRSTENTER] = false
    }
  }
}