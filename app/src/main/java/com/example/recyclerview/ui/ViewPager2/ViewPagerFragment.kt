package com.example.recyclerview.ui.TextWithCartoonsSeparately

import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.recyclerview.*
import me.relex.circleindicator.CircleIndicator3

class ViewPagerFragment: Fragment() {
    
    private lateinit var view_pager: ViewPager2

    private lateinit var textWithCartoonTogetherViewModel: ViewPagerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        textWithCartoonTogetherViewModel =
            ViewModelProvider(this).get(ViewPagerViewModel::class.java)
        val root = inflater.inflate(R.layout.view_pager_main, container, false)

        val btnNext: Button = root.findViewById(R.id.btn_next)
        btnNext.setOnClickListener { 
            val current = view_pager.currentItem
            val items: Int = ViewPagerAdapter(fillNames(), fillImages()).itemCount
            if (current < items - 1)
                view_pager.setCurrentItem(current + 1, true) }

        val btnBack: Button = root.findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            val current = view_pager.currentItem
            if (current != 0)
                view_pager.setCurrentItem(current - 1, true)
        }

        textWithCartoonTogetherViewModel.text.observe(viewLifecycleOwner, Observer {

            view_pager = root.findViewById(R.id.view_pager2)
            view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            view_pager.adapter = ViewPagerAdapter(fillNames(), fillImages())
            val indicator: CircleIndicator3 = root.findViewById(R.id.indicator)
            indicator.setViewPager(view_pager)

        })
        return root
    }

    private fun fillNames(): List<String> {
        return this.resources.getStringArray(R.array.cats).toList()
    }

    private fun fillImages(): List<Int> {
        return this.getImageId(R.array.cats_cartoons)
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