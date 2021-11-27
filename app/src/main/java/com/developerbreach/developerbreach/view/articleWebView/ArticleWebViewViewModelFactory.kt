package com.developerbreach.developerbreach.view.articleWebView

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ArticleWebViewViewModelFactory(
    private val application: Application,
    private val articleUrlLink: String?
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleWebViewViewModel::class.java)) {
            return ArticleWebViewViewModel(application, articleUrlLink) as T
        }
        throw IllegalArgumentException("Cannot create Instance for ArticleWebViewViewModel class")
    }
}