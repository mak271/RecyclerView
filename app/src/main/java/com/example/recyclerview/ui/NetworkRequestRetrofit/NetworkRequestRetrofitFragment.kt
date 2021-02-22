package com.example.recyclerview.ui.NetworkRequestRetrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recyclerview.R

class NetworkRequestRetrofitFragment: Fragment() {

    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.network_retrofit_main, container, false)

        root.apply {
            textView = findViewById(R.id.tvResponse_retrofit)

            findViewById<Button>(R.id.btn_get_retrofit).setOnClickListener {
                textView.text = "Выполняется GET запрос..."
                val query = 1

                Network.getRequest(query, { responseModel, responseCode ->
                    if (responseModel != null) showModelInfo(responseModel)
                    else textView.text = "Ошибка: Model is NULL"
                }, { error ->
                    textView.text = "Ошибка: ${error.message}"
                })
            }

            findViewById<Button>(R.id.btn_post_retrofit).setOnClickListener {

                textView.text = "Выполняется POST запрос..."

                val modelForRequest = ResponseModel().apply {
                    title = "Welcome to the Hell"
                    body = "'Hi' from Lucifer"
                    userId = 666
                }

                Network.postRequest(modelForRequest, { responseModel, responseCode ->
                    if (responseModel != null) showModelInfo(responseModel)
                    else textView.text = "Model is NULL"
                }, { error ->
                    textView.text = "Error: ${error.message}"
                })
            }


        }

        return root
    }

    private fun showModelInfo(responseModel: ResponseModel) {
        textView.apply {
            text = "Title: ${responseModel.title}\n\n"
            append("ID: ${responseModel.id}\n\n")
            append("UserID: ${responseModel.userId}\n\n")
            append("Body: ${responseModel.body}\n\n")
        }
    }



}





















