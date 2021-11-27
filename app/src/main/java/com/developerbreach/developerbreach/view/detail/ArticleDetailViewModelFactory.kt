package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory

class ArticleDetailViewModelFactory(
    private val application: Application,
    private val articleId: Int?
) : AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
            return ArticleDetailViewModel(application, articleId) as T
        }
        throw IllegalArgumentException("Cannot create Instance for ArticleDetailViewModel class")
    }
}