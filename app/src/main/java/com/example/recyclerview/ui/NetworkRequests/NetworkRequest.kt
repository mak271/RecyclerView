package com.example.recyclerview.ui.NetworkRequests

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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
                textView.text = "Result:"
                textView.append(result)
            }
        }
    }


}





















