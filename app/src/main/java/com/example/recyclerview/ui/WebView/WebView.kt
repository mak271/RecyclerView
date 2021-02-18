package com.example.recyclerview.ui.WebView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.recyclerview.R

class WebView: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.web_view_main, container, false)

        val webView: WebView = root.findViewById(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://google.com")

        val editText: EditText = root.findViewById(R.id.edit_text)

        root.findViewById<Button>(R.id.btn_web).setOnClickListener {
            var url = editText.text.toString()

            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = String.format("https://%s", url)

            if (url != webView.url)
                webView.loadUrl(url)

        }

        return root
    }



}





















