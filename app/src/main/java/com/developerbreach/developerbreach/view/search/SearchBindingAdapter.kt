package com.developerbreach.developerbreach.view.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.itemViewAnimation
import com.developerbreach.developerbreach.controller.AppNavDirections
import java.util.*


@BindingAdapter("bindSearchRecyclerViewData")
fun RecyclerView.setSearchRecyclerViewData(
    list: List<Article>?
) {
    val adapter = SearchAdapter()
    adapter.submitList(list)
    this.adapter = adapter
}


@BindingAdapter("bindSearchToDetailListener", "bindSearchItemViewGroup")
fun TextView.setSearchToDetailListener(
    article: Article,
    frameLayout: FrameLayout
) {
    val textView = this
    textView.setOnClickListener {

        itemViewAnimation(
            context = textView.context,
            view = textView,
            duration = 250L,
            animationProperty = R.anim.fade_enter_anim
        ).setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationEnd(p0: Animation?) {
                navigateOnAnimationCompleted(article, frameLayout, textView, findNavController())
            }

            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationStart(p0: Animation?) {}
        })
    }
}

private fun navigateOnAnimationCompleted(
    article: Article,
    frameLayout: FrameLayout,
    textView: TextView,
    navController: NavController
) {
    TransitionManager.beginDelayedTransition(frameLayout, Fade())
    AppNavDirections(navController).searchToDetail(article, textView)
}


@BindingAdapter(
    "bindClearSearchQueryData", "bindSearchViewModelHeader",
    "bindSearchRecyclerViewHeader", "bindSearchErrorTextHeader"
)
fun ImageView.setClearSearchQueryData(
    editText: AppCompatEditText,
    filteredList: List<Article>?,
    recyclerView: RecyclerView,
    searchTextError: TextView
) {
    val clearImageView = this
    editText.addTextChangedListener(object : TextWatcher {

        override fun onTextChanged(query: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (query!!.isNotEmpty()) {
                clearImageView.visibility = View.VISIBLE
            } else if (query.isEmpty()) {
                clearImageView.visibility = View.INVISIBLE
            }

            val formatQueryBeforeSearch = query.toString().lowercase(Locale.getDefault())
            filterSearchQuery(
                formatQueryBeforeSearch,
                filteredList,
                recyclerView,
                searchTextError
            )
        }

        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    })

    this.setOnClickListener {
        editText.setText("")
    }
}

/**
 * @param query contains search query entered by user in editText.
 * We pass this query to filter results by converting to string query.
 *
 * Listener which implements TextWatcher which notifies query has been changed for request of
 * the new data.
 */
private fun filterSearchQuery(
    query: String,
    unFilteredList: List<Article>?,
    recyclerView: RecyclerView,
    searchTextError: TextView
) {
    if (query.isNotEmpty()) {
        val filterList = onFilterChanged(query, unFilteredList)
        attachAdapter(filterList, recyclerView)
        toggleRecyclerView(filterList, recyclerView, searchTextError)
    } else if (query.isEmpty()) {
        attachAdapter(unFilteredList, recyclerView)
    }
}

/**
 * @param query get user query and perform search operation using onQueryChanged() method
 * using the return value of liveData object from [.getSearchList].
 * @return return matching search results internally.
 */
private fun onFilterChanged(
    query: String, list: List<Article>?
): List<Article> {
    val filteredList = ArrayList<Article>()
    if (list != null) {
        for (article in list) {
            val formatTitle: String = article.name.lowercase(Locale.getDefault())
            if (formatTitle.contains(query)) {
                filteredList.add(article)
            }
        }
    }
    return filteredList
}

private fun attachAdapter(
    list: List<Article>?,
    recyclerView: RecyclerView
) {
    val adapter = SearchAdapter()
    adapter.submitList(list)
    recyclerView.adapter = adapter
}

/**
 * @param articleList contains list data of searchable articles.
 */
private fun toggleRecyclerView(
    articleList: List<Article>,
    recyclerView: RecyclerView,
    searchTextError: TextView
) {
    // If no items are available, hide the recyclerView and show not found text error.
    if (articleList.isEmpty()) {
        recyclerView.visibility = View.INVISIBLE
        searchTextError.visibility = View.VISIBLE
        // If items available, show recyclerView hide error textView.
    } else {
        recyclerView.visibility = View.VISIBLE
        searchTextError.visibility = View.INVISIBLE
    }
}


@BindingAdapter("bindSearchEmptyStateImage")
fun ImageView.setSearchEmptyStateImage(
    listSize: Int?
) {
    if (listSize == 0) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
