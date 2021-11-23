package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import java.net.URL


fun getArticles(
    totalPostsToDoRunQueryOn: Int
): List<Article> {
    val response = NetworkResponse.articleResponse(totalPostsToDoRunQueryOn)
    return JsonRemoteData.fetchArticleJsonData(response)
}

fun getArticleDetails(
    articleId: Int
): ArticleDetail {
    val response = NetworkResponse.articleDetailResponse(articleId)
    return JsonRemoteData.fetchArticleJsonDataById(response)
}

fun getSearchableArticles(
    totalPostsToDoRunQueryOn: Int
): List<Search> {
    val response = NetworkResponse.searchResponse(totalPostsToDoRunQueryOn)
    return JsonRemoteData.fetchSearchableArticlesJsonData(response)
}

fun getArticlesByCategoryId(
    categoryId: Int,
    postsPage: Int
): List<Article> {
    val response = NetworkResponse.articleResponseByCategoryId(categoryId, postsPage)
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

internal object NetworkResponse {

    fun articleResponse(
        totalPostsToDoRunQueryOn: Int
    ): String {
        val uriString: String = QueryBuilder.articleBuilder(totalPostsToDoRunQueryOn)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun articleDetailResponse(
        articleId: Int
    ): String {
        val uriString: String = QueryBuilder.articleByIdBuilder(articleId)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun searchResponse(
        totalPostsToDoRunQueryOn: Int
    ): String {
        val uriString: String = QueryBuilder.articleBuilder(totalPostsToDoRunQueryOn)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun articleResponseByCategoryId(
        categoryId: Int,
        postsPage: Int
    ): String {
        val uriString: String = QueryBuilder.articlesByCategoryBuilder(categoryId, postsPage)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun categoryResponse(): String {
        val uriString: String = QueryBuilder.categoryBuilder()
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun authorResponse(): String {
        val uriString: String = QueryBuilder.authorBuilder()
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }

    fun authorResponseById(authorId: Int): String {
        val uriString: String = QueryBuilder.authorBuilderById(authorId)
        val requestUrl: URL = createUrl(uriString)
        return getResponseFromHttpUrl(requestUrl)
    }
}
