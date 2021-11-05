package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import java.io.IOException
import java.net.URL


fun getArticles(): List<Article> {
    val response = NetworkResponse.articleResponse()
    return JsonRemoteData.fetchArticleJsonData(response)
}

fun getArticleDetails(articleId: Int): ArticleDetail {
    val response = NetworkResponse.articleDetailResponse(articleId)
    return JsonRemoteData.fetchArticleJsonDataById(response)
}

fun getSearchableArticles(): List<Search> {
    val response = NetworkResponse.searchResponse()
    return JsonRemoteData.fetchSearchableArticlesJsonData(response)
}

fun getArticlesByCategory(
    categoryId: Int
): List<Article> {
    val response = NetworkResponse.articleResponseByCategoryId(categoryId)
    return JsonRemoteData.fetchArticleJsonData(response)
}

fun getCategories(): List<Categories> {
    val response = NetworkResponse.categoryResponse()
    return JsonRemoteData.fetchCategoriesJsonData(response)
}

fun getAuthors(): List<Authors> {
    val response = NetworkResponse.authorResponse()
    return JsonRemoteData.fetchAuthorsJsonData(response)
}

fun getAuthorById(
    authorId: Int
): Pair<String, String> {
    val response = NetworkResponse.authorResponseById(authorId)
    return JsonRemoteData.fetchAuthorDataById(response)
}

object NetworkResponse {

    @Throws(IOException::class)
    fun articleResponse(): String {
        val uriString: String = QueryBuilder.articleBuilder(8)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun articleDetailResponse(articleId: Int): String {
        val uriString: String = QueryBuilder.articleByIdBuilder(articleId)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun searchResponse(): String {
        val uriString: String = QueryBuilder.articleBuilder(28)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun articleResponseByCategoryId(
        categoryId: Int
    ): String {
        val uriString: String = QueryBuilder.articlesByCategoryBuilder(categoryId)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun categoryResponse(): String {
        val uriString: String = QueryBuilder.categoryBuilder()
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun authorResponse(): String {
        val uriString: String = QueryBuilder.authorBuilder()
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    @Throws(IOException::class)
    fun authorResponseById(authorId: Int): String {
        val uriString: String = QueryBuilder.authorBuilderById(authorId)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }
}
