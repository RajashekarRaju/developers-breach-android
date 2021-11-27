package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import java.net.URL

class NetworkService {

    fun getArticles(
        totalPostsToDoRunQueryOn: Int
    ): List<Article> {
        val response = articleResponse(totalPostsToDoRunQueryOn)
        return JsonData.fetchArticleJsonData(response)
    }

    fun getArticleDetails(
        articleId: Int?
    ): ArticleDetail {
        val response = articleDetailResponse(articleId)
        return JsonData.fetchArticleJsonDataById(response)
    }

    fun getSearchableArticles(
        totalPostsToDoRunQueryOn: Int
    ): List<Search> {
        val response = searchResponse(totalPostsToDoRunQueryOn)
        return JsonData.fetchSearchableArticlesJsonData(response)
    }

    fun getArticlesByCategoryId(
        categoryId: Int,
        postsPage: Int
    ): List<Article> {
        val response = articleResponseByCategoryId(categoryId, postsPage)
        return JsonData.fetchArticleJsonData(response)
    }

    fun getCategories(): List<Categories> {
        val response = categoryResponse()
        return JsonData.fetchCategoriesJsonData(response)
    }

    fun getAuthors(): List<Authors> {
        val response = authorResponse()
        return JsonData.fetchAuthorsJsonData(response)
    }

    fun getAuthorById(
        authorId: Int
    ): Pair<String, String> {
        val response = authorResponseById(authorId)
        return JsonData.fetchAuthorDataById(response)
    }

    private companion object {

        private val builder = QueryBuilder

        private fun getResponse(
            url: URL
        ): String {
            val queryUtils = NetworkQueryUtils()
            return queryUtils.getResponseFromHttpUrl(url)
        }

        private fun getUrl(
            stringUrl: String
        ): URL {
            val queryUtils = NetworkQueryUtils()
            return queryUtils.createUrl(stringUrl)
        }

        fun articleResponse(
            totalPostsToDoRunQueryOn: Int
        ): String {
            val uriString: String = builder.articleBuilder(totalPostsToDoRunQueryOn)
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun articleDetailResponse(
            articleId: Int?
        ): String {
            val uriString: String = builder.articleByIdBuilder(articleId)
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun searchResponse(
            totalPostsToDoRunQueryOn: Int
        ): String {
            val uriString: String = builder.articleBuilder(totalPostsToDoRunQueryOn)
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun articleResponseByCategoryId(
            categoryId: Int,
            postsPage: Int
        ): String {
            val uriString: String = builder.articlesByCategoryBuilder(categoryId, postsPage)
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun categoryResponse(): String {
            val uriString: String = builder.categoryBuilder()
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun authorResponse(): String {
            val uriString: String = builder.authorBuilder()
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }

        fun authorResponseById(
            authorId: Int
        ): String {
            val uriString: String = builder.authorBuilderById(authorId)
            val requestUrl: URL = getUrl(uriString)
            return getResponse(requestUrl)
        }
    }
}
