package com.developerbreach.developerbreach.view.detail

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar


@BindingAdapter("bindCollapsingBannerImage")
fun ImageView.setImageResource(
    urlString: String?
) {
    val imageView = this
    Glide.with(this.context).load(urlString).centerCrop().into(this)

    this.setOnClickListener {
        TransitionManager.beginDelayedTransition(imageView.rootView as ViewGroup, Fade())
        AppNavDirections(findNavController()).detailToBanner(urlString, this)
    }
}


@BindingAdapter("bindButtonWebView")
fun MaterialButton.setButtonWebView(
    articleUrlLink: String?
) {
    this.setOnClickListener {
        AppNavDirections(findNavController()).detailToArticleWebView(articleUrlLink)
    }
}


@BindingAdapter("bindArticleDetailDatePosted")
fun TextView.setArticleDetailDatePosted(
    datePosted: String?
) {
    val formatDate: String? = datePosted?.dropLast(9)
    this.text = formatDate
}


@BindingAdapter(
    "bindArticleAuthorDetailImage",
    "bindArticleAuthorTitle"
)
fun ImageView.setArticleAuthorDetailImage(
    authorData: Pair<String, String>?,
    authorTextView: TextView
) {
    val authorName = authorData?.first
    authorTextView.text = authorName

    val authorAvatarUrl = authorData?.second
    Glide.with(this.context)
        .load(authorAvatarUrl)
        .circleCrop()
        .into(this)
}


@BindingAdapter("bindArticleExcerpt")
fun TextView.setArticleExcerpt(
    excerpt: String?
) {
    val formatExcerpt: String? = excerpt?.drop(3)?.dropLast(5)
    this.text = formatExcerpt
}


@BindingAdapter("bindAddArticleToFavorites")
fun MaterialButton.setAddArticleToFavorites(
    viewModel: ArticleDetailViewModel,
) {
    this.setOnClickListener { view ->
        viewModel.insertFavorite()
        Snackbar.make(
            view,
            this.context.getString(R.string.snackbar_added_to_favorites_message),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}