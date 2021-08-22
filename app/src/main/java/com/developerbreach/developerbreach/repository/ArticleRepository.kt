package com.developerbreach.developerbreach.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.repository.database.ArticleDatabase
import com.developerbreach.developerbreach.repository.database.entity.asDatabaseModel
import com.developerbreach.developerbreach.repository.database.entity.asDomainModel
import com.developerbreach.developerbreach.repository.network.getArticles
import com.developerbreach.developerbreach.repository.network.getCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException


class ArticleRepository(
    private val database: ArticleDatabase
) {

    suspend fun getArticlesData(): List<Article> {
        val listData: List<Article>
        withContext(Dispatchers.IO) {
            listData = getArticles()
        }
        return listData
    }

    suspend fun getCategoriesData(): List<Categories> {
        val listData: List<Categories>
        withContext(Dispatchers.IO) {
            listData = getCategories()
        }
        return listData
    }

    val favorites: LiveData<List<Article>> =
        Transformations.map(database.favoriteDao.loadAllFavorites()) { favoritesEntityList ->
            favoritesEntityList.asDomainModel()
        }

    suspend fun refreshArticles() {
        withContext(Dispatchers.IO) {
            try {
                val articlesList: List<Article> = getArticles()

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
        var searchableArticles: List<Article>
        withContext(Dispatchers.IO) {
            searchableArticles = getArticles()
        }
        return searchableArticles
    }
}