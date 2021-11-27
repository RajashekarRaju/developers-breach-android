package com.developerbreach.developerbreach.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Search
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


class SearchViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val networkRepository = (application as DevelopersBreachApp).networkRepository

    private val _filteredArticles = MutableLiveData<List<Search>>()
    val filteredArticles: LiveData<List<Search>>
        get() = _filteredArticles

    private val totalPostsToDoRunQueryOn = 100

    private var searchableArticles = listOf<Search>()

    init {
        loadSearchableArticlesToFilter()
    }

    private fun loadSearchableArticlesToFilter() {
        viewModelScope.launch {
            try {
                searchableArticles =
                    networkRepository.getSearchableArticlesData(totalPostsToDoRunQueryOn)
            } catch (e: Exception) {
                Timber.e("Exception caught in SearchViewModel ${e.message}")
            }
        }
    }

    /**
     * @param query get user query and perform search operation using onQueryChanged() method
     * using the return value of liveData object from [.getSearchList].
     * @return return matching search results internally.
     */
    fun getSearchableArticlesWithQuery(
        query: String
    ) {
        viewModelScope.launch {
            val filteredList = ArrayList<Search>()
            for (currentArticle in searchableArticles) {
                val formatTitle: String = currentArticle.name.lowercase(Locale.getDefault())
                if (formatTitle.contains(query)) {
                    filteredList.add(currentArticle)
                }
            }
            _filteredArticles.postValue(filteredList)
        }
    }
}