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
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.itemViewAnimation
import java.util.*


@BindingAdapter("bindSearchRecyclerViewData", "bindSearchFragmentReference")
fun RecyclerView.setSearchRecyclerViewData(
    viewModel: SearchViewModel,
    viewLifecycleOwner: SearchFragment
) {
    // With variable viewModel start observing LiveData to this fragment by calling articles()
    // and observe changes for list of searchable article data.
    // articles() is externally exposed in ViewModel class to observe changes.
    viewModel.articles.observe(viewLifecycleOwner, { articleList ->
        val adapter = SearchAdapter()
        // Pass list to adapter calling submitList since our adapter class extends to ListAdapter<>.
        adapter.submitList(articleList)
        // Set adapter with recyclerView.
        this.adapter = adapter
    })
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
    navController.navigate(
        SearchFragmentDirections.searchToDetail(article),
        FragmentNavigatorExtras(textView to article.name)
    )
}


@BindingAdapter(
    "bindClearSearchQueryData", "bindSearchViewModelHeader",
    "bindSearchFragmentReferenceHeader", "bindSearchRecyclerViewHeader", "bindSearchErrorTextHeader"
)
fun ImageView.setClearSearchQueryData(
    editText: AppCompatEditText,
    viewModel: SearchViewModel,
    owner: SearchFragment,
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
                viewModel,
                owner,
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
    viewModel: SearchViewModel,
    viewLifecycleOwner: SearchFragment,
    recyclerView: RecyclerView,
    searchTextError: TextView
) {

    viewModel.filter(query).observe(viewLifecycleOwner, { articleList ->
        // Only perform this operation is query is valid.
        if (query.isNotEmpty()) {
            // Get our adapter
            val adapter = SearchAdapter()
            // Set adapter to recyclerView and call submitList with articles fragment observes.
            adapter.submitList(articleList)
            recyclerView.adapter = adapter
            // Change behaviour of recyclerView based on data available.
            toggleRecyclerView(articleList, recyclerView, searchTextError)
        }
    })
}

/**
 * @param articleList contains list data of searchable articles.
 */
private fun toggleRecyclerView(
    articleList: List<Article?>,
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
