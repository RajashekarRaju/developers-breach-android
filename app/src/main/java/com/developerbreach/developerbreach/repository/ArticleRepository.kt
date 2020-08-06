package com.developerbreach.developerbreach.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.NetworkArticlesContainer
import com.developerbreach.developerbreach.model.asDatabaseModel
import com.developerbreach.developerbreach.repository.database.ArticleDatabase
import com.developerbreach.developerbreach.repository.database.entity.ArticleEntity
import com.developerbreach.developerbreach.repository.database.entity.asDatabaseModel
import com.developerbreach.developerbreach.repository.database.entity.asDomainModel
import com.developerbreach.developerbreach.repository.network.getArticles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


class ArticleRepository(
        private val database: ArticleDatabase
) {

    val articles: LiveData<List<Article>> =
            Transformations.map(database.articleDao.loadAllArticles()) { articleEntityList ->
                articleEntityList.asDomainModel()
            }

    val favorites: LiveData<List<Article>> =
            Transformations.map(database.favoriteDao.loadAllFavorites()) { favoritesEntityList ->
                favoritesEntityList.asDomainModel()
            }

    suspend fun refreshArticles() {
        withContext(Dispatchers.IO) {
            try {
                val articlesList: NetworkArticlesContainer = getArticles()
                database.articleDao.insertArticles(articlesList.asDatabaseModel())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    suspend fun insertArticle(
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

    suspend fun deleteAllFavorites() {
        withContext(Dispatchers.IO) {
            database.favoriteDao.deleteAllFavorites()
        }
    }

    suspend fun searchableArticle(): List<Article> {
        var searchableArticles = ArrayList<Article>()
        withContext(Dispatchers.IO) {
            val articleEntity: List<ArticleEntity> = database.articleDao.getSearchableArticles()
            searchableArticles = articleEntity.asDomainModel() as ArrayList<Article>
        }
        return searchableArticles
    }
}