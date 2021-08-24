package com.developerbreach.developerbreach.view.favorites

import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.*
import com.google.android.material.card.MaterialCardView


@BindingAdapter("bindFavoritesListData", "bindFavoriteFragmentReference",
        "bindFavoritesNotFoundText")
fun RecyclerView.setFavoritesListData(
        viewModel: FavoritesViewModel,
        owner: FavoritesFragment,
        noFavoritesFound: TextView
) {
    viewModel.favorites.observe(owner, { favorites ->
        val adapter = FavoritesAdapter(viewModel, owner)
        // Pass list to adapter calling submitList since our adapter class extends to ListAdapter<>.
        adapter.submitList(favorites)
        // Set adapter with recyclerView.
        this.adapter = adapter
        // Change behaviour of recyclerView based on data available.
        toggleRecyclerView(favorites, noFavoritesFound, this)
    })
}

/**
 * @param favorites contains list data of favorites.
 */
private fun toggleRecyclerView(
        favorites: List<Article>,
        noFavoritesFoundText: TextView,
        recyclerView: RecyclerView
) {
    // If no items are available, hide the recyclerView and show not found text error.
    if (favorites.isEmpty()) {
        recyclerView.visibility = View.INVISIBLE
        noFavoritesFoundText.visibility = View.VISIBLE
        // If items available, show recyclerView hide error textView.
    } else {
        recyclerView.visibility = View.VISIBLE
        noFavoritesFoundText.visibility = View.INVISIBLE
    }
}


@BindingAdapter("bindFavoriteToDetailClickListener", "bindArticleItemTitleTransition")
fun MaterialCardView.setFavoriteToDetailClickListener(
        article: Article,
        title: TextView
) {
    this.setOnClickListener {
        val direction: NavDirections =
                FavoritesFragmentDirections.favoritesToDetailFragment(article)

        val extras = FragmentNavigatorExtras(
                this to article.name
        )

        TransitionManager.beginDelayedTransition(this, Fade())
        title.visibility = View.GONE

        findNavController().navigate(direction, extras)
    }
}


@BindingAdapter("bindFavoriteItemBanner")
fun ImageView.setFavoriteItemBanner(
        imageUrl: String
) {
    Glide.with(this.context).load(imageUrl).into(this)
}


@BindingAdapter("bindFavoriteItemTitle")
fun TextView.setFavoriteItemTitle(
        title: String
) {
    this.text = capitalizeWord(title)
}


@BindingAdapter(
        "bindFavoriteFragmentModel", "bindFavoriteViewModel",
        "bindItemCardView", "bindImageViewFragment"
)
fun ImageView.bindDeleteFavoriteClickListener(
        article: Article,
        viewModel: FavoritesViewModel,
        cardView: MaterialCardView,
        fragment: FavoritesFragment
) {
    this.setOnClickListener {
        // Create a simple fade out animation to let user know the selected article has been
        // removed after clicking the imageView.
        val animation = itemViewAnimation(cardView.context, cardView, 500L,
                R.anim.fade_exit_anim)
        startCircularEffect(cardView, cardView.right, cardView.top)
        deleteAfterAnimation(this, animation, viewModel, fragment, article)
    }
}


private fun deleteAfterAnimation(
        imageView: ImageView,
        cardFadeOutAnimation: Animation,
        viewModel: FavoritesViewModel,
        fragment: FavoritesFragment,
        article: Article
) {
    // Attach a listener and perform delete operation.
    cardFadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationEnd(animation: Animation) {
            viewModel.deleteArticle(article)
            showSnackBar(
                    imageView.context.getString(R.string.snackbar_removed_favorite_message),
                    fragment.requireActivity()
            )
        }

        override fun onAnimationStart(animation: Animation) {}
        override fun onAnimationRepeat(animation: Animation) {}
    })
}
