package com.example.recyclerview.ui.OnlyCartoon

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

class OnlyCartoonFragment : Fragment() {

    private lateinit var onlyCartoonViewModel: OnlyCartoonViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        onlyCartoonViewModel =
                ViewModelProvider(this).get(OnlyCartoonViewModel::class.java)
        val root = inflater.inflate(R.layout.main_content, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rcView)

        onlyCartoonViewModel.text.observe(viewLifecycleOwner, Observer {

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = MyAdapterOnlyCartoons(fill())
        })
        return root
    }

    private fun fill() : List<Int> {
        return this.getImageId(R.array.cat_image)
    }

    fun getImageId(imageArrayId:Int):List<Int>
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