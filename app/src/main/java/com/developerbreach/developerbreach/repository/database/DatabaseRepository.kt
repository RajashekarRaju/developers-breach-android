package com.developerbreach.developerbreach.repository.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.developerbreach.developerbreach.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository(
    private val database: ArticleDatabase
) {

    val favorites: LiveData<List<Article>> =
        Transformations.map(database.favoriteDao.loadAllFavorites()) { favoritesEntityList ->
            favoritesEntityList.asDomainModel()
        }

    suspend fun insertArticleToFavorites(
        article: Article
    ) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.insertFavoriteArticle(article.asDatabaseModel())
        }
    }

    suspend fun deleteSelectedFavorite(
        article: Article
    ) {
        withContext(Dispatchers.IO) {
            database.favoriteDao.deleteFavoriteArticle(article.asDatabaseModel())
        }
    }
}