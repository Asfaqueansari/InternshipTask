package com.example.internshiptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.internshiptask.adapter.CategoryItemAdapter
import com.example.internshiptask.model.Category
import com.example.internshiptask.model.CategoryRepository
import com.example.internshiptask.viewmodel.CategoryViewModel
import com.example.internshiptask.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var adapter: CategoryItemAdapter

    companion object {
        const val TAG = "CONSOLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        setUI()
        onClicks()
        viewModel.loadCategoryData()
    }

    private fun setUI() {
        adapter = CategoryItemAdapter(viewModel.categories.value ?: emptyList())
        recycler_view.layoutManager = GridLayoutManager(this, 3)
        recycler_view.adapter = adapter
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(CategoryRepository()))
            .get(CategoryViewModel::class.java)
        viewModel.categories.observe(this, renderCategory)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)

    }
    //observers
    private val renderCategory = Observer<List<Category>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        adapter.update(it.shuffled())
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
    }

    private fun onClicks() {
        btn_next.setOnClickListener {
            //
            var selecedItemName = " "
            val list = adapter.getSelectedList()
            for (i in list) {
                selecedItemName += i.hindititle!! + ","
            }
            Toast.makeText(
                this,
                " आपने चुना है: ${selecedItemName.removeSuffix(",")}.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
