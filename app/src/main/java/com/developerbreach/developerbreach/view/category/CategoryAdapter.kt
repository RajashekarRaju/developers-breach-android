package com.developerbreach.developerbreach.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemCategoriesBinding
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.view.category.CategoryAdapter.CategoryViewHolder

class CategoryAdapter : ListAdapter<Categories, CategoryViewHolder>(Categories.DiffCallback) {

    class CategoryViewHolder(
        private val binding: ItemCategoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            categories: Categories
        ) {
            binding.category = categories
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        // Allow DataBinding to inflate the layout.
        return CategoryViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categories: Categories = getItem(position)
        holder.bind(categories)
    }
}