package com.example.recyclerview.ui.TextWithCartoonsSeparately

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextWithCartoonsSeparatelyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "вава"
    }

    val text: LiveData<String> = _text
}