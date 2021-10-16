package com.developerbreach.developerbreach.view.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.capitalizeWord
import com.google.android.material.card.MaterialCardView


@BindingAdapter("bindArticlesListDataByCategory")
fun RecyclerView.setArticleListDataByCategory(
    list: List<Article>?
) {
    val adapter = ArticleAdapter()
    // Pass list to adapter calling submitList since our adapter class extends to ListAdapter<>.
    adapter.submitList(list)
    // Set adapter with recyclerView.
    this.adapter = adapter

    if (list?.isNotEmpty() == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


@BindingAdapter("bindSelectedCategoryText")
fun TextView.setSelectedCategoryText(
    categoryName: String?
) {
    if (categoryName.isNullOrEmpty()) {
        this.text = this.resources.getString(R.string.category_tag)
    } else {
        this.text = categoryName
    }
}


@BindingAdapter("bindSelectedCategoryIcon")
fun ImageView.setSelectedCategoryIcon(
    categoryName: String?
) {
    var icon = 0
    when (categoryName) {
        context.getString(R.string.category_title_android) -> icon = R.drawable.ic_android
        context.getString(R.string.category_title_firebase) -> icon = R.drawable.ic_firebase
        context.getString(R.string.category_title_kotlin) -> icon = R.drawable.ic_kotlin
        context.getString(R.string.category_title_machine_learning) -> icon = R.drawable.ic_ml
        context.getString(R.string.category_title_material_design) -> icon = R.drawable.ic_mdc
        context.getString(R.string.category_title_compose) -> icon = R.drawable.ic_compose
        context.getString(R.string.category_title_uncategorized) -> this.visibility = View.GONE
    }
    this.setImageResource(icon)
}

/**
 * BindingAdapters for fragment class [ArticleListFragment].
 * This values will be called as attributes in fragment layout [R.layout.item_article].
 * Setters for binding from layout is set in adapter class [ArticleAdapter].
 *
 * When value articleItemTitle is used as attribute on TextView, the method bindArticleItemTitle
 * is called.
 *
 * TextView to set a String value to it.
 * @param title    contains String value article title to be set to TextView.
 */
@BindingAdapter("bindArticleItemName")
fun TextView.setArticleItemTitle(
    title: String
) {
    this.text = capitalizeWord(title)
}


@BindingAdapter("bindArticleItemBanner")
fun ImageView.setArticleItemBanner(
    imageUrl: String
) {
    val requestOptions = RequestOptions().transform(
        CenterCrop(),
        RoundedCorners(20)
    )
    Glide.with(this.context)
        .load(imageUrl)
        .apply(requestOptions)
        .into(this)
}


@BindingAdapter("bindArticleToDetailListener", "bindArticleItemTitleTransition")
fun MaterialCardView.setArticleToDetailClickListener(
    article: Article,
    title: TextView
) {
    val card = this
    card.setOnClickListener {
        TransitionManager.beginDelayedTransition(card, Fade())
        title.visibility = View.GONE
        AppNavDirections(findNavController()).articlesListToDetail(article, card)
    }
}


@BindingAdapter("bindArticlesEmptyStateImage")
fun ImageView.setArticlesEmptyStateImage(
    listSize: Int?
) {
    if (listSize == 0) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
