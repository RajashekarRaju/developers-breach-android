package com.developerbreach.developerbreach.view.category

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.view.list.ArticleListViewModel
import com.google.android.material.card.MaterialCardView


@BindingAdapter(
    "bindItemCategoryParentListener",
    "bindArticleListViewModel"
)
fun MaterialCardView.setItemCategoryParentListener(
    category: Categories,
    viewModel: ArticleListViewModel,
) {
    this.setOnClickListener {
        viewModel.saveUserSelectedCategory(category)
    }
}


@BindingAdapter("bindArticlesCategoryListData", "bindArticleListViewModel")
fun RecyclerView.setArticlesCategoryListData(
    list: List<Categories>?,
    viewModel: ArticleListViewModel
) {
    val adapter = CategoryAdapter(viewModel)
    adapter.submitList(list)
    this.adapter = adapter
}


@BindingAdapter(
    "bindItemCategoryIconImageView",
    "bindItemCategoryParentCardView",
    "bindItemCategoryTitleTextView"
)
fun ImageView.setItemCategoryIconImageView(
    categories: Categories,
    parent: MaterialCardView,
    title: TextView
) {
    var icon = R.drawable.ic_android
    when (categories.categoryName) {
        context.getString(R.string.category_title_android) -> icon = R.drawable.ic_android
        context.getString(R.string.category_title_firebase) -> icon = R.drawable.ic_firebase
        context.getString(R.string.category_title_kotlin) -> icon = R.drawable.ic_kotlin
        context.getString(R.string.category_title_machine_learning) -> icon = R.drawable.ic_ml
        context.getString(R.string.category_title_material_design) -> icon = R.drawable.ic_mdc
        context.getString(R.string.category_title_compose) -> icon = R.drawable.ic_compose
        context.getString(R.string.category_title_uncategorized) -> {
            this.visibility = View.GONE
            parent.visibility = View.GONE
            title.visibility = View.GONE
        }
    }
    this.setImageResource(icon)
}