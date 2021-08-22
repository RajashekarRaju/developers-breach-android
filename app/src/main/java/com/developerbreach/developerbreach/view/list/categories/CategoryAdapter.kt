package com.developerbreach.developerbreach.view.list.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.databinding.ItemCategoryBinding
import com.developerbreach.developerbreach.model.Categories

class CategoryAdapter(
    private val categoriesList: List<Categories>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(
        private val binding: ItemCategoryBinding
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
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categories: Categories = categoriesList[position]
        holder.bind(categories)
    }

    override fun getItemCount() = categoriesList.size
}