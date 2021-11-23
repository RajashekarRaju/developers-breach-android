package com.developerbreach.developerbreach.view.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import com.developerbreach.developerbreach.utils.DataState
import kotlinx.coroutines.launch
import timber.log.Timber


class ArticleListViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    private val articleDatabase = getDatabaseInstance(application.applicationContext)
    private val repository = AppRepository(articleDatabase)

    private val _articles = MutableLiveData<List<Article>>()
    val articlesByCategory: LiveData<List<Article>>
        get() = _articles

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>>
        get() = _categories

    private val _selectedCategoryName = MutableLiveData<String>()
    val selectedCategoryName: LiveData<String>
        get() = _selectedCategoryName

    private val _categoriesState = MutableLiveData<DataState>()
    val categoriesDataState: LiveData<DataState>
        get() = _categoriesState

    private val _articlesState = MutableLiveData<DataState>()
    val articlesState: LiveData<DataState>
        get() = _articlesState

    private var loadNextPage: Int = 1

    private lateinit var selectedCategory: Categories

    fun saveUserSelectedCategory(category: Categories) {
        loadNextPage = 1
        selectedCategory = category
        loadArticlesWithSelectedCategory()
    }

    init {
        launchToLoadCategoriesData()
    }

    private fun launchToLoadCategoriesData() {
        viewModelScope.launch {
            _categoriesState.value = DataState.LOADING
            try {
                val categoriesData = repository.getCategoriesData()
                _categories.postValue(categoriesData)
                _categoriesState.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in ArticleListViewModel ${e.message}")
                _categoriesState.value = DataState.ERROR
            }
        }
    }

    fun loadMoreArticles() {
        loadNextPage += 1
        loadArticlesWithSelectedCategory()
        Timber.e("Current page $loadNextPage")
    }

    private fun loadArticlesWithSelectedCategory() {
        viewModelScope.launch {
            _articlesState.value = DataState.LOADING
            try {
                val articlesById = repository.getArticlesByCategory(
                    selectedCategory.categoryId,
                    loadNextPage
                )
                _articles.postValue(articlesById)
                _selectedCategoryName.postValue(selectedCategory.categoryName)
                _articlesState.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in ArticleListViewModel ${e.message}")
                _articlesState.value = DataState.ERROR
            }
        }
    }
}