package com.example.internshiptask.model

import com.example.internshiptask.data.OperationCallback

interface CategoryDataSource {
    fun retrieveCategory(callback:OperationCallback)
}