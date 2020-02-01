package com.example.internshiptask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshiptask.data.OperationCallback
import com.example.internshiptask.model.Category
import com.example.internshiptask.model.CategoryDataSource

class CategoryViewModel(private val repository: CategoryDataSource) : ViewModel() {

    private val categoriesLoading = MutableLiveData<List<Category>>().apply { value = emptyList() }
    val categories: LiveData<List<Category>> = categoriesLoading

    private val viewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = viewLoading

    private val messageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = messageError

    fun loadCategoryData() {
        viewLoading.postValue(true)
        repository.retrieveCategory(object : OperationCallback {

            override fun onError(obj: Any?) {
                viewLoading.postValue(false)
                messageError.postValue(obj)
            }

            override fun onSuccess(obj: Any?) {
                viewLoading.postValue(false)

                if (obj != null && obj is List<*>) {
                    categoriesLoading.value = obj as List<Category>
                }
            }
        })
    }

}