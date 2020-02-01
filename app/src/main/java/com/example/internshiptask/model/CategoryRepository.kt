package com.example.internshiptask.model
import com.example.internshiptask.data.ApiClient
import com.example.internshiptask.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository : CategoryDataSource {


    override fun retrieveCategory(callback: OperationCallback) {
       val call = ApiClient.build()?.categories()
        call?.enqueue(object : Callback<List<Category>> {
            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                response.body()?.let {
                    callback.onSuccess(it)
                }
            }
        })
    }
}