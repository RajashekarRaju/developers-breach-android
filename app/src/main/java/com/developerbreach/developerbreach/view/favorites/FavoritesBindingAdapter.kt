package com.developerbreach.developerbreach.view.favorites

import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.controller.AppNavDirections
import com.google.android.material.card.MaterialCardView


@BindingAdapter("bindFavoritesListData", "bindFavoritesNotFoundText", "bindFavoriteViewModel")
fun RecyclerView.setFavoritesListData(
    list: List<Article>?,
    noFavoritesFound: ImageView,
    viewModel: FavoritesViewModel
) {
    val adapter = FavoritesAdapter(viewModel)
    adapter.submitList(list)
    this.adapter = adapter
    toggleRecyclerView(list, noFavoritesFound, this)
}

/**
 * @param favorites contains list data of favorites.
 */
private fun toggleRecyclerView(
    favorites: List<Article>?,
    noFavoritesFoundImage: ImageView,
    recyclerView: RecyclerView
) {
    // If no items are available, hide the recyclerView and show not found text error.
    if (favorites?.isEmpty() == true) {
        recyclerView.visibility = View.INVISIBLE
        noFavoritesFoundImage.visibility = View.VISIBLE
    } else {
        recyclerView.visibility = View.VISIBLE
        noFavoritesFoundImage.visibility = View.INVISIBLE
    }
}


@BindingAdapter("bindFavoriteToDetailClickListener", "bindArticleItemTitleTransition")
fun MaterialCardView.setFavoriteToDetailClickListener(
    articleId: Int,
    title: TextView
) {
    this.setOnClickListener {
        TransitionManager.beginDelayedTransition(this, Fade())
        title.visibility = View.GONE

        AppNavDirections(findNavController()).fragmentsToDetailFragment(articleId, this)
    }
}


@BindingAdapter("bindFavoriteItemBanner")
fun ImageView.setFavoriteItemBanner(
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


@BindingAdapter("bindFavoriteItemTitle")
fun TextView.setFavoriteItemTitle(
    title: String
) {
    this.text = capitalizeWord(title)
}


@BindingAdapter(
    "bindFavoriteFragmentModel", "bindFavoriteViewModel", "bindItemCardView"
)
fun ImageView.bindDeleteFavoriteClickListener(
    article: Article,
    viewModel: FavoritesViewModel,
    cardView: MaterialCardView,
) {
    this.setOnClickListener {
        // Create a simple fade out animation to let user know the selected article has been
        // removed after clicking the imageView.
        val animation = itemViewAnimation(
            cardView.context, cardView, 500L,
            R.anim.fade_exit_anim
        )
        startCircularEffect(cardView, cardView.right, cardView.top)
        deleteAfterAnimation(this, animation, viewModel, article)
    }
}

private fun deleteAfterAnimation(
    imageView: ImageView,
    cardFadeOutAnimation: Animation,
    viewModel: FavoritesViewModel,
    article: Article
) {
    // Attach a listener and perform delete operation.
    cardFadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            viewModel.deleteArticle(article)
            Toast.makeText(
                imageView.context,
                imageView.context.getString(R.string.snackbar_removed_favorite_message),
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationRepeat(animation: Animation) {}
    })
}
