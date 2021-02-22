package com.example.recyclerview.ui.NetworkRequestRetrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {

    private var retrofit: Retrofit? = null

    companion object {
        private const val url = "https://jsonplaceholder.typicode.com/"
        private var networkService: NetworkService? = null

        fun getNetworkService(): NetworkService? {
            if (networkService == null)
                networkService = NetworkService()
            return networkService
        }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApi(): ApiService? {
        return retrofit?.create(ApiService::class.java)
    }

}