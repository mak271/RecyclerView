package com.example.recyclerview.ui.NetworkRequestRetrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/posts/{id}")
    fun getModelByID(@Path("id") id: Int): Call<ResponseModel>

    @POST("/posts")
    fun postData(@Body data: ResponseModel): Call<ResponseModel>

}