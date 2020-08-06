package com.developerbreach.developerbreach.view.detail

import android.annotation.SuppressLint
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Article
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


@BindingAdapter("bindCollapsingBannerImage")
fun ImageView.setImageResource(
        urlString: String
) {
    Glide.with(this.context).load(urlString).into(this)

    this.setOnClickListener {
        val direction: NavDirections =
                ArticleDetailFragmentDirections.detailToBannerFragment(urlString)
        findNavController().navigate(direction)
    }
}


@BindingAdapter("bindButtonWebView")
fun Button.setButtonWebView(
        article: Article
) {
    this.text = this.context.getString(R.string.open_article_button_text)
    this.setOnClickListener {
        val action: NavDirections =
                ArticleDetailFragmentDirections.detailToArticleWebViewFragment(article)
        findNavController().navigate(action)
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("bindAuthorAndData")
fun TextView.setAuthorAndDate(
        date: String
) {
    val formatDate: String = date.dropLast(9)
    val author: String = this.context.getString(R.string.author_name_tools_text)
    this.text = "$author  |  $formatDate"
}


@BindingAdapter("bindArticleExcerpt")
fun TextView.setArticleExcerpt(
        excerpt: String
) {
    val formatExcerpt: String = excerpt.drop(3).dropLast(5)
    this.text = formatExcerpt
}


@BindingAdapter("bindDetailViewModel", "bindNavigateUpFromDetail")
fun FloatingActionButton.setDetailFab(
        viewModel: ArticleDetailViewModel,
        toolbar: Toolbar
) {
    this.setOnClickListener { view ->
        viewModel.insertFavorite(viewModel.selectedArticle)
        Snackbar.make(
                view,
                this.context.getString(R.string.snackbar_added_to_favorites_message),
                Snackbar.LENGTH_SHORT
        ).show()
    }

    toolbar.setNavigationOnClickListener {
        findNavController().navigateUp()
    }
}