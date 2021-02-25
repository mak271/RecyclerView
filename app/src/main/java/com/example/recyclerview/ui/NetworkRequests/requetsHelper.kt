package com.example.recyclerview.ui.NetworkRequests

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection

suspend fun makeGetRequest(): String {
    return withContext(Dispatchers.IO) {
        var buffer: BufferedReader? = null

        try {
            val url = URL("https://api.openweathermap.org/data/2.5/onecall?lat=57&lon=41&exclude=minutely,hourly&units=metric&&lang=en&appid=891a6b403aaa5b51c2d5612a5c717d3e")
            val httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.requestMethod = "GET"
            httpsURLConnection.readTimeout = 10000
            httpsURLConnection.connect()

            buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))

            val builder = StringBuilder()
            var line: String

            while (true) {
                line = buffer.readLine() ?: break
                builder.append(line).append("")
            }
            builder.toString()
        } catch (exc: Exception) {
            buffer?.close()
            exc.message.toString()
        } finally {
            buffer?.close()
        }
    }
}

suspend fun makePostRequest(): String {
    return withContext(Dispatchers.IO) {
        var buffer: BufferedReader? = null

        val dates = JSONObject()
        dates.apply {
            put("title", "Welcome")
            put("body", "To the HELL")
            put("userId", 666)
        }


        try {

            val url = URL("https://jsonplaceholder.typicode.com/posts")
            val httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.setRequestProperty("Content-Type", "application/json")
            httpsURLConnection.requestMethod = "POST"
            httpsURLConnection.readTimeout = 10000
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = true
            httpsURLConnection.connect()

            val outputStream = BufferedOutputStream(httpsURLConnection.outputStream)
            val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
            writer.write(dates.toString())
            writer.flush()

            buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
            val builder = StringBuilder()
            var line: String

            while (true) {
                line = buffer.readLine() ?: break
                builder.append(line).append("")
            }
            builder.toString()

        } catch (exc: Exception) {
            buffer?.close()
            exc.message.toString()
        } finally {
            buffer?.close()
        }
    }
}




















