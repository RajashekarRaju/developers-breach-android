package com.developerbreach.developerbreach.view.home

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
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.capitalizeWord
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.developerbreach.developerbreach.utils.itemViewAnimation
import com.developerbreach.developerbreach.view.list.ArticleAdapter
import com.developerbreach.developerbreach.view.list.ArticleListFragment
import com.google.android.material.card.MaterialCardView


@BindingAdapter("bindRecentArticlesListData")
fun RecyclerView.setRecentArticleFragmentListData(
    list: List<Article>?,
) {
    val adapter = RecentArticlesAdapter()
    adapter.submitList(list)
    this.adapter = adapter
    itemViewAnimation(context, this, 1000L, R.anim.fade_enter_anim)
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
@BindingAdapter("bindRecentArticleItemName")
fun TextView.setRecentArticleItemTitle(
    title: String
) {
    this.text = capitalizeWord(title)
}


@BindingAdapter("bindRecentArticleItemBanner")
fun ImageView.setRecentArticleItemBanner(
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


@BindingAdapter("bindRecentArticleToDetailListener", "bindRecentArticleItemTitleTransition")
fun MaterialCardView.setRecentArticleToDetailClickListener(
    articleId: Int,
    title: TextView
) {
    this.setOnClickListener {

        TransitionManager.beginDelayedTransition(this, Fade())
        title.visibility = View.GONE

        AppNavDirections(findNavController()).homeToDetail(articleId, this)
    }
}