package com.example.recyclerview.ui.JSON

import android.Manifest
import android.app.usage.ExternalStorageStats
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class JSON: Fragment() {

    private val fileName = "RadioStations.json"
    private val filePath = "MyFolder"

    private var externalFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.json_main, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_radio)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        val adapter = MyAdapterJSON(fill())
        recyclerView.adapter = adapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1000)
            }
        }

        if (isExternalStorageWritable())
            externalFile = File(context?.getExternalFilesDir(filePath), fileName)
        else
            throw Exception("Нет хранилища данных")

        root.findViewById<Button>(R.id.btn_convert).setOnClickListener {
            val selectedList = adapter.getSelectedList()
            if (selectedList.isEmpty()) {
                Toast.makeText(context, "Выделите элемент", Toast.LENGTH_SHORT).show()
            }
            else {
                val jsonArray = JSONArray()
                selectedList.forEach {
                    val jsonObject = JSONObject()
                    jsonObject.put("name", it.name)
                    jsonObject.put("stream", it.stream)
                    jsonObject.put("image", it.img)

                    jsonArray.put(jsonObject)
                }
                val jsonString = jsonArray.toString()

                var output: FileOutputStream? = null

                try {

                    output = FileOutputStream(externalFile)
                    output.write(jsonString.toByteArray())

                } catch (exc: Exception) {
                    exc.printStackTrace()
                } finally {
                    output?.close()
                }

                adapter.clearSelection()
                Toast.makeText(context, "Файл сохранён", Toast.LENGTH_SHORT).show()

            }
        }

        return root
    }


    private fun fill(): List<RadioModel> {
        val list = mutableListOf<RadioModel>()
        val raw = resources.openRawResource(R.raw.radio)
        val streamReader = raw.reader().readText()
        val json = JSONObject(streamReader)
        val stations = json.getJSONArray("stations")
        for (i in 0 until stations.length()) {
            val obj = JSONObject(stations.get(i).toString())
            val name = obj.getString("name")
            val stream = obj.getString("stream")
            val image = obj.getString("image")
            val item = RadioModel(name, stream, image)
            list.add(item)
        }
        return list
    }

    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }


}





















