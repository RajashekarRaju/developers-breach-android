package com.developerbreach.developerbreach.view.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Article
import kotlinx.coroutines.launch

/**
 * @param application provides application context for ViewModel. With this context get reference
 * to other classes like repository.
 */
class FavoritesViewModel(
    application: Application
) : AndroidViewModel(application) {

    // Get a new instance from this app repository using application context.
    // From repository call source which has observable favorites list data and assign it.
    private val databaseRepository = (application as DevelopersBreachApp).databaseRepository

    private val _favorites: LiveData<List<Article>> = databaseRepository.favorites
    val favorites: LiveData<List<Article>>
        get() = _favorites

    fun deleteArticle(
        article: Article
    ) {
        viewModelScope.launch {
            databaseRepository.deleteSelectedFavorite(article)
        }
    }
}