package com.example.recyclerview.ui.NetworkRequests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class NetworkRequest: Fragment() {

    lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.network_main, container, false)
        root.apply {

            textView = findViewById(R.id.tvResponse)

            findViewById<Button>(R.id.btn_get).setOnClickListener {
                getRequest()
            }

            findViewById<Button>(R.id.btn_post).setOnClickListener {
                postRequest()
            }

        }

        return root
    }

    private fun getRequest() {
        GlobalScope.launch {
            val result = makeGetRequest()
            withContext(Dispatchers.Main) { textView.text = result }
        }
    }

    private fun postRequest() {
        GlobalScope.launch {
            val result = makePostRequest()
            withContext(Dispatchers.Main) {
                val obj = JSONObject(result)
                textView.text = "POST request:"
                textView.append(obj.toString())
            }
        }
    }


}





















