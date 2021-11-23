package com.developerbreach.developerbreach.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.developerbreach.developerbreach.model.*
import com.developerbreach.developerbreach.repository.database.ArticleDatabase
import com.developerbreach.developerbreach.repository.database.entity.asDatabaseModel
import com.developerbreach.developerbreach.repository.database.entity.asDomainModel
import com.developerbreach.developerbreach.repository.network.*
import com.developerbreach.developerbreach.repository.network.getArticles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AppRepository(
    private val database: ArticleDatabase
) {

    suspend fun getArticlesData(
        totalPostsToDoRunQueryOn: Int
    ): List<Article> {
        val listData: List<Article>
        withContext(Dispatchers.IO) {
            listData = getArticles(totalPostsToDoRunQueryOn)
        }
        return listData
    }

    suspend fun getArticlesDetailData(
        articleId: Int
    ): ArticleDetail {
        val data: ArticleDetail
        withContext(Dispatchers.IO) {
            data = getArticleDetails(articleId)
        }
        return data
    }

    suspend fun getSearchableArticlesData(
        totalPostsToDoRunQueryOn: Int
    ): List<Search> {
        val listData: List<Search>
        withContext(Dispatchers.IO) {
            listData = getSearchableArticles(totalPostsToDoRunQueryOn)
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

    suspend fun getArticlesByCategory(
        categoryId: Int,
        postsPage: Int
    ): List<Article> {
        val listData: List<Article>
        withContext(Dispatchers.IO) {
            listData = getArticlesByCategoryId(categoryId, postsPage)
        }
        return listData
    }

    suspend fun getAuthorsData(): List<Authors> {
        val listData: List<Authors>
        withContext(Dispatchers.IO) {
            listData = getAuthors()
        }
        return listData
    }

    suspend fun getAuthorDataById(authorId: Int): Pair<String, String> {
        val listData: Pair<String, String>
        withContext(Dispatchers.IO) {
            listData = getAuthorById(authorId)
        }
        return listData
    }

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