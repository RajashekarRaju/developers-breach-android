package com.developerbreach.developerbreach.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.ArticleDetail
import com.developerbreach.developerbreach.networkManager.DataState
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import kotlinx.coroutines.*
import timber.log.Timber


class ArticleDetailViewModel(
    application: Application,
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository,
    private val articleId: Int
) : AndroidViewModel(application) {

    private val _authorData = MutableLiveData<Pair<String, String>>()
    val authorData: LiveData<Pair<String, String>>
        get() = _authorData

    private val _articleDetailData = MutableLiveData<ArticleDetail>()
    val articleDetailData: LiveData<ArticleDetail>
        get() = _articleDetailData

    private val _detailState = MutableLiveData<DataState>()
    val detailState: LiveData<DataState>
        get() = _detailState

    private lateinit var articleData: ArticleDetail

    init {
        loadSelectedArticleDetails()
    }

    private fun loadSelectedArticleDetails() {
        viewModelScope.launch {
            _detailState.value = DataState.LOADING
            try {
                articleData = networkRepository.getArticlesDetailData(articleId)
                _articleDetailData.postValue(articleData)
                _authorData.postValue(networkRepository.getAuthorDataById(articleData.authorId))
                _detailState.value = DataState.DONE
            } catch (e: Exception) {
                Timber.e("Exception caught in ArticleDetailViewModel ${e.message}")
                _detailState.value = DataState.ERROR
            }
        }
    }

    fun insertFavorite() {
        viewModelScope.launch {
            databaseRepository.insertArticleToFavorites(
                Article(
                    articleData.articleId,
                    articleData.name,
                    articleData.banner
                )
            )
        }
    }

    companion object {

        fun provideFactory(
            application: Application,
            networkRepository: NetworkRepository,
            databaseRepository: DatabaseRepository,
            articleId: Int,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
                        return ArticleDetailViewModel(
                            application,
                            networkRepository,
                            databaseRepository,
                            articleId
                        ) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for ArticleDetailViewModel class")
                }
            }
        }
    }
}