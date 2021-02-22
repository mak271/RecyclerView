package com.example.recyclerview.ui.NetworkRequestRetrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseModel {
    @SerializedName("userId")
    @Expose
    var userId = 0

    @SerializedName("id")
    @Expose
    var id = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("body")
    @Expose
    var body: String? = null

}