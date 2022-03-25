package com.developerbreach.developerbreach.ui.search

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Search
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList


class SearchViewModel(
    application: Application,
    private val repository: NetworkRepository
) : AndroidViewModel(application) {

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
                searchableArticles = repository.getSearchableArticlesData(totalPostsToDoRunQueryOn)
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

    companion object {

        fun provideFactory(
            application: Application,
            repository: NetworkRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                        return SearchViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for SearchViewModel class")
                }
            }
        }
    }
}