package com.developerbreach.developerbreach.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.ArticleRepository
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

    private val repository = ArticleRepository(getDatabaseInstance(application))
    private var viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    private val _articles: LiveData<List<Article>> = repository.articles
    val articles: LiveData<List<Article>>
        get() = _articles

    private lateinit var searchableArticle: List<Article>
    private val filteredList = MutableLiveData<List<Article>>()

    init {
        viewModelScope.launch {
            searchableArticle = repository.searchableArticle()
        }
    }

    /**
     * @param query get user query and perform search operation using onQueryChanged() method
     * using the return value of liveData object from [.getSearchList].
     * @return return matching search results internally.
     */
    fun filter(query: String): LiveData<List<Article>> {
        filterWithQuery(query)
        return filteredList
    }

    /**
     * We run search query on existing database in background thread and return those values.
     *
     * @param query contains query given by user in editText field.
     */
    private fun filterWithQuery(query: String) {
        val filterList = ArrayList<Article>()
        for (article: Article in searchableArticle) {
            val formatTitle: String = article.name.toLowerCase(Locale.getDefault())
            if (formatTitle.contains(query)) {
                filterList.add(article)
            }
        }
        filteredList.value = filterList
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}