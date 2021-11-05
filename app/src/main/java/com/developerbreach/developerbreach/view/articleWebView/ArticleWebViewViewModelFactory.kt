package com.developerbreach.developerbreach.view.articleWebView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * A AndroidViewModelFactory for creating a instance of [ArticleWebViewViewModel]
 * AndroidViewModel for fragment class [ArticleWebViewFragment] with a constructor.
 */
class ArticleWebViewViewModelFactory
/**
 * Creates a [ViewModelProvider.AndroidViewModelFactory]
 *
 * @param application parameter to pass in [AndroidViewModel]
 * @param articleUrlLink a user selected Article object to pass in [AndroidViewModel]
 */
internal constructor(
    private val application: Application,
    private val articleUrlLink: String?
) : ViewModelProvider.AndroidViewModelFactory(application) {

    /**
     * @param modelClass to check if our [ArticleWebViewViewModel] model class is assignable.
     * @param <T>        type of generic class
     * @return returns the ViewModel class with passing parameters if instance is created.
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleWebViewViewModel::class.java)) {
            return ArticleWebViewViewModel(application, articleUrlLink) as T
        }
        throw IllegalArgumentException("Cannot create Instance for ArticleWebViewViewModel class")
    }

}