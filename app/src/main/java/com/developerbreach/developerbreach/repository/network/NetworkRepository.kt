package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(
    private val networkService: NetworkService
) {

    suspend fun getArticlesData(
        totalPostsToDoRunQueryOn: Int
    ): List<Article> {
        val listData: List<Article>
        withContext(Dispatchers.IO) {
            listData = networkService.getArticles(totalPostsToDoRunQueryOn)
        }
        return listData
    }

    suspend fun getArticlesDetailData(
        articleId: Int?
    ): ArticleDetail {
        val data: ArticleDetail
        withContext(Dispatchers.IO) {
            data = networkService.getArticleDetails(articleId)
        }
        return data
    }

    suspend fun getSearchableArticlesData(
        totalPostsToDoRunQueryOn: Int
    ): List<Search> {
        val listData: List<Search>
        withContext(Dispatchers.IO) {
            listData = networkService.getSearchableArticles(totalPostsToDoRunQueryOn)
        }
        return listData
    }

    suspend fun getCategoriesData(): List<Categories> {
        val listData: List<Categories>
        withContext(Dispatchers.IO) {
            listData = networkService.getCategories()
        }
        return listData
    }

    suspend fun getArticlesByCategory(
        categoryId: Int,
        postsPage: Int
    ): List<Article> {
        val listData: List<Article>
        withContext(Dispatchers.IO) {
            listData = networkService.getArticlesByCategoryId(categoryId, postsPage)
        }
        return listData
    }

    suspend fun getAuthorsData(): List<Authors> {
        val listData: List<Authors>
        withContext(Dispatchers.IO) {
            listData = networkService.getAuthors()
        }
        return listData
    }

    suspend fun getAuthorDataById(authorId: Int): Pair<String, String> {
        val listData: Pair<String, String>
        withContext(Dispatchers.IO) {
            listData = networkService.getAuthorById(authorId)
        }
        return listData
    }
}