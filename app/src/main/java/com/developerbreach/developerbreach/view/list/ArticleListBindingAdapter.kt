package com.developerbreach.developerbreach.view.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.capitalizeWord
import com.developerbreach.developerbreach.utils.showSnackBar
import com.google.android.material.card.MaterialCardView


@BindingAdapter("bindArticlesListData", "bindArticleListFragmentReference")
fun RecyclerView.setArticleFragmentListData(
        viewModel: ArticleListViewModel,
        owner: ArticleListFragment
) {
    viewModel.articles.observe(owner, Observer { articles ->
        val adapter = ArticleAdapter(viewModel, owner)
        // Pass list to adapter calling submitList since our adapter class extends to ListAdapter<>.
        adapter.submitList(articles)
        // Set adapter with recyclerView.
        this.adapter = adapter
    })
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
    Glide.with(this.context).load(imageUrl).into(this)
}


@BindingAdapter("bindArticleToDetailListener", "bindArticleItemTitleTransition")
fun MaterialCardView.setArticleToDetailClickListener(
        article: Article,
        title: TextView
) {
    this.setOnClickListener {
        val direction: NavDirections =
                ArticleListFragmentDirections.articleListToDetailFragment(article)

        val extras = FragmentNavigatorExtras(
                this to article.name
        )

        TransitionManager.beginDelayedTransition(this, Fade())
        title.visibility = View.GONE

        findNavController().navigate(direction, extras)
    }
}


@BindingAdapter("bindArticleFragmentModel", "bindArticleViewModel",
        "bindArticleListFragmentItemReference")
fun ImageView.setAddArticleToFavoritesListener(
        article: Article,
        viewModel: ArticleListViewModel,
        fragment: ArticleListFragment
) {
    this.setImageResource(R.drawable.ic_favorite_add)
    this.setOnClickListener {
        viewModel.insertFavorite(article)
        showSnackBar(
                this.context.getString(R.string.snackbar_added_to_favorites_message),
                fragment.requireActivity()
        )
    }
}
