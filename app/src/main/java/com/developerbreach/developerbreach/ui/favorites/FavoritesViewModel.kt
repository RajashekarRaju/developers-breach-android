package com.developerbreach.developerbreach.ui.favorites

import android.app.Application
import androidx.lifecycle.*
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import kotlinx.coroutines.launch

/**
 * @param application provides application context for ViewModel. With this context get reference
 * to other classes like repository.
 */
class FavoritesViewModel(
    application: Application,
    private val repository: DatabaseRepository
) : AndroidViewModel(application) {

    private val _favorites: LiveData<List<Article>> = repository.favorites
    val favorites: LiveData<List<Article>>
        get() = _favorites

    fun deleteArticle(
        article: Article
    ) {
        viewModelScope.launch {
            repository.deleteSelectedFavorite(article)
        }
    }

    companion object {

        fun provideFactory(
            application: Application,
            repository: DatabaseRepository,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                        return FavoritesViewModel(application, repository) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for FavoritesViewModel class")
                }
            }
        }
    }
}