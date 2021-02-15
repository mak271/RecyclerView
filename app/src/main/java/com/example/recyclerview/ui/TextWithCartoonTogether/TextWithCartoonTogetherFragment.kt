package com.example.recyclerview.ui.TextWithCartoonTogether

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class TextWithCartoonTogetherFragment : Fragment() {

    private lateinit var textWithCartoonTogetherViewModel: TextWithCartoonTogetherViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        textWithCartoonTogetherViewModel =
                ViewModelProvider(this).get(TextWithCartoonTogetherViewModel::class.java)
        val root = inflater.inflate(R.layout.main_content, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rcView)

        textWithCartoonTogetherViewModel.text.observe(viewLifecycleOwner, Observer {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = MyAdapterTextWithCartoonsTogether(fillText(), fillImg())
        })
        return root
    }

    private fun fillText(): List<String> {
        return this.resources.getStringArray(R.array.cat_names).toList()
    }

    private fun fillImg(): List<Int> {
        return this.getImageId(R.array.cat_image)
    }

    private fun getImageId(imageArrayId:Int):List<Int>
    {
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for (i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids.toList()
    }

}