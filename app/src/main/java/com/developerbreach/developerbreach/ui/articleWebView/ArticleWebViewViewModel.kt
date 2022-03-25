package com.developerbreach.developerbreach.ui.articleWebView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ArticleWebViewViewModel(
    application: Application,
    private val articleUrlLink: String?
) : AndroidViewModel(application) {

    companion object {

        fun provideFactory(
            application: Application,
            articleUrlLink: String?,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ArticleWebViewViewModel::class.java)) {
                        return ArticleWebViewViewModel(application, articleUrlLink) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for ArticleWebViewViewModel class")
                }
            }
        }
    }
}