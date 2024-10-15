package com.example.lab_6_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RectangleViewModel : ViewModel() {

    private val _rectangles = MutableLiveData<MutableList<RectangleItem>>().apply {
        value = mutableListOf()
    }
    val rectangles: LiveData<MutableList<RectangleItem>> = _rectangles

    private var currentId = 1

    fun addRectangle() {
        _rectangles.value?.add(RectangleItem(currentId, "Прямоугольник $currentId"))
        currentId++
        _rectangles.postValue(_rectangles.value)
    }

    fun removeRectangle(position: Int) {
        _rectangles.value?.removeAt(position)
        _rectangles.postValue(_rectangles.value)
    }
}