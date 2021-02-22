package com.example.recyclerview.ui.NetworkRequestRetrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Network {

    fun getRequest(
        query: Int,
        onComplete: (response: ResponseModel?, responseCode:Int) -> Unit,
        onFail: (t: Throwable) -> Unit
    )
    {

        NetworkService.getNetworkService()?.getApi()?.getModelByID(query)?.enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    val model: ResponseModel? = response.body()
                    onComplete(model, response.code())
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    onFail(t)
                }
            }
        )

    }

    fun postRequest(
        model: ResponseModel,
        onComplete: (response: ResponseModel?, responseCode: Int) -> Unit,
        onFail: (t: Throwable) -> Unit
    )
    {
        NetworkService.getNetworkService()?.getApi()?.postData(model)?.enqueue(
            object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    val resModel: ResponseModel? = response.body()
                    onComplete(resModel, response.code())
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    onFail(t)
                }

            }
        )
    }

}