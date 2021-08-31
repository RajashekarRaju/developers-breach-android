package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.model.Categories
import java.io.IOException
import java.net.URL


fun getArticles(): List<Article> {
    val response = NetworkResponse.articleResponse()
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

object NetworkResponse {

    @Throws(IOException::class)
    fun articleResponse(): String {
        val uriString: String = QueryBuilder.articleBuilder()
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
}
