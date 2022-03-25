package com.developerbreach.developerbreach.ui.category

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.networkManager.DataState
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import kotlinx.coroutines.launch
import timber.log.Timber


class CategoryViewModel(
    application: Application,
    private val repository: NetworkRepository
) : AndroidViewModel(application) {

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

    private lateinit var selectedCategory: Categories

    private var loadNextPage: Int = 1

    var isFirstTimeScrolled = true

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

    companion object {

        fun provideFactory(
            application: Application,
            repository: NetworkRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
                        return CategoryViewModel(application, repository,) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for CategoryViewModel class")
                }
            }
        }
    }
}