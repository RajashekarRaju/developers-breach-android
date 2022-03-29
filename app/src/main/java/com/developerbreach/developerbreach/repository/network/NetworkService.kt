package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import com.developerbreach.developerbreach.utils.NetworkQueryUtils

/**
 * Class contains functions which can fetch data from network.
 * This is the only network data source for whole app.
 */
class NetworkService {

    // Contains all url endpoints to json data
    private val requestUrls by lazy { RequestUrls() }

    // Returns all json responses
    private val jsonData by lazy { JsonData() }

    // To make http request calls
    private val queryUtils by lazy { NetworkQueryUtils() }

    /**
     * @return the result of latest list of articles fetched from the network.
     */
    fun getArticles(
        totalPostsToDoRunQueryOn: Int
    ): List<Article> {
        val requestUrl = requestUrls.articleBuilder(totalPostsToDoRunQueryOn)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchArticleJsonData(response)
    }

    fun getArticleDetails(
        articleId: Int?
    ): ArticleDetail {
        val requestUrl = requestUrls.articleByIdBuilder(articleId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchArticleDetailsJsonDataById(response)
    }

    fun getSearchableArticles(
        totalPostsToDoRunQueryOn: Int
    ): List<Search> {
        val requestUrl = requestUrls.articleBuilder(totalPostsToDoRunQueryOn)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchSearchableArticlesJsonData(response)
    }

    fun getArticlesByCategoryId(
        categoryId: Int,
        currentPage: Int
    ): List<Article> {
        val requestUrl = requestUrls.articlesByCategoryBuilder(categoryId, currentPage)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchArticleJsonData(response)
    }

    fun getCategories(): List<Categories> {
        val requestUrl = requestUrls.categoryBuilder()
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchCategoriesJsonData(response)
    }

    fun getAuthors(): List<Authors> {
        val requestUrl = requestUrls.authorBuilder()
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchAuthorsJsonData(response)
    }

    fun getAuthorById(
        authorId: Int
    ): Pair<String, String> {
        val requestUrl = requestUrls.authorBuilderById(authorId)
        val response = queryUtils.getResponseFromHttpUrl(requestUrl)
        return jsonData.fetchAuthorDataById(response)
    }
}
