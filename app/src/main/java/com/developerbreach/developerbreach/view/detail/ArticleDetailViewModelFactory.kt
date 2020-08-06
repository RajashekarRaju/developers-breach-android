package com.developerbreach.developerbreach.view.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.developerbreach.developerbreach.model.Article

/**
 * A AndroidViewModelFactory for creating a instance of [ArticleDetailViewModel]
 * AndroidViewModel for fragment class [ArticleDetailFragment] with a constructor.
 */
class ArticleDetailViewModelFactory
/**
 * Creates a [ViewModelProvider.AndroidViewModelFactory]
 *
 * @param application parameter to pass in [AndroidViewModel]
 * @param article     a user selected Article object to pass in [AndroidViewModel]
 */
internal constructor(
        private val application: Application,
        private val article: Article
) : AndroidViewModelFactory(application) {

    /**
     * @param modelClass to check if our [ArticleDetailViewModel] model class is assignable.
     * @param <T>        type of generic class
     * @return returns the ViewModel class with passing parameters if instance is created.
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
            return ArticleDetailViewModel(application, article) as T
        }
        throw IllegalArgumentException("Cannot create Instance for ArticleDetailViewModel class")
    }

}