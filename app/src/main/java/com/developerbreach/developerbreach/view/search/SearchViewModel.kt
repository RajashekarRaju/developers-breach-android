package com.developerbreach.developerbreach.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Search
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class SearchViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = AppRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _searchableArticles = MutableLiveData<List<Search>>()
    val searchableArticles: LiveData<List<Search>>
        get() = _searchableArticles

    private var articles = listOf<Search>()

    init {
        viewModelScope.launch {
            articles = repository.getSearchableArticlesData()
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
            for (currentArticle in articles) {
                val formatTitle: String = currentArticle.name.lowercase(Locale.getDefault())
                if (formatTitle.contains(query)) {
                    filteredList.add(currentArticle)
                }
            }
            _searchableArticles.postValue(filteredList)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}