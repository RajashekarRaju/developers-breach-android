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
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView


@BindingAdapter(
    "bindArticlesListDataByCategory",
    "bindArticleListViewModel",
)
fun RecyclerView.setArticleListDataByCategory(
    list: List<Article>?,
    viewModel: ArticleListViewModel
) {
    val adapter = ArticleAdapter()
    // Pass list to adapter calling submitList since our adapter class extends to ListAdapter<>.
    adapter.submitList(list)
    // Set adapter with recyclerView.
    this.adapter = adapter

    this.addOnScrollListener(articlesScrollListener(viewModel))

    if (list?.isNotEmpty() == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

private fun articlesScrollListener(
    viewModel: ArticleListViewModel
): RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        val bottomDirection = 1
        val canScrollVertically = recyclerView.canScrollVertically(bottomDirection)

        if (!canScrollVertically) {
            when (newState) {
                RecyclerView.SCROLL_STATE_IDLE -> {
                    if (viewModel.isFirstTimeScrolled) {
                        viewModel.isFirstTimeScrolled = false
                        viewModel.loadMoreArticles()
                    }
                }
                RecyclerView.SCROLL_STATE_DRAGGING -> {
                    viewModel.isFirstTimeScrolled = true
                }
            }
        }
    }
}


@BindingAdapter("bindSelectedCategoryText")
fun MaterialToolbar.setSelectedCategoryText(
    categoryName: String?
) {
    if (categoryName.isNullOrEmpty()) {
        this.title = this.resources.getString(R.string.category_tag)
    } else {
        this.title = categoryName
    }
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
    articleId: Int,
    title: TextView
) {
    val card = this
    card.setOnClickListener {
        TransitionManager.beginDelayedTransition(card, Fade())
        title.visibility = View.GONE
        AppNavDirections(findNavController()).fragmentsToDetailFragment(articleId, card)
    }
}


@BindingAdapter("bindArticlesEmptyStateImage")
fun ImageView.setArticlesEmptyStateImage(
    listSize: Int?
) {
    when (listSize) {
        0 -> this.visibility = View.VISIBLE
        else -> this.visibility = View.GONE
    }
}
