package com.fsd.quvideo.network.service

import com.example.testapp.network.Network

interface HomeService {
  companion object{
    fun instance(): HomeService = Network.createService(HomeService::class.java)
  }
//  @GET("category/list")
//  suspend fun category():CategoryResponse
//  @GET("recommand/banner")
//  suspend fun banner(): SwiperResponse
}