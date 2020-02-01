package com.example.internshiptask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internshiptask.R
import com.example.internshiptask.model.Category
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.category_list_item.view.*

class CategoryItemAdapter(private var categories: List<Category>) :
    RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    fun update(data: List<Category>) {
        categories = data
        notifyDataSetChanged()
    }

    fun getSelectedList() = categories.filter { it.isSelected }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], position)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category, position: Int) {

            Glide.with(view.category_image.context).load(category.categoryImage)
                .into(view.category_image)

            view.tv_hindi_title.text = category.hindititle

            checkForSelection(category)
            onClicks(position)
        }

        private fun onClicks(position: Int) {
            view.setOnClickListener {
                categories[position].isSelected = !categories[position].isSelected
                notifyDataSetChanged()
            }
        }

        private fun checkForSelection(category: Category) {
            if (category.isSelected) {
                view.category_image.setBackgroundResource(R.drawable.dashed_border)
                view.check_icon.visibility = View.VISIBLE
            } else {
                view.category_image.setBackgroundResource(android.R.color.white)
                view.check_icon.visibility = View.GONE
            }
        }
    }
}