package com.developerbreach.developerbreach.repository.network

import android.net.Uri
import java.net.URL

/**
 * Builds and returns URL used to fetch data from the server.
 */
class RequestUrls {

    /**
     * https://developersbreach.com/wp-json/wp/v2/posts?per_page=5
     */
    fun articleBuilder(
        numberOfPostsToQuery: Int
    ): URL {
        val params = "$QUERY_PARAMETER_COUNT_PER_PAGE=$numberOfPostsToQuery"
        return URL("${BASE_URL}/$PATH_POSTS?$params")
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/posts?include={10920}
     * [articleId] updates list with new content.
     */
    fun articleByIdBuilder(
        articleId: Int?
    ): URL {
        val params = "$QUERY_PARAMETER_POST_INCLUDE_ID=$articleId"
        return URL("${BASE_URL}/$PATH_POSTS?$params")
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{posts}
     * [categoryId] updates articles list with based on selected category Id.
     *
     * https://developersbreach.com/wp-json/wp/v2/posts?categories=2713721
     * https://developersbreach.com/wp-json/wp/v2/posts?per_page=2&page=1&categories=2713553&orderby=date
     */
    fun articlesByCategoryBuilder(
        categoryId: Int,
        currentPage: Int,
        numOfPostsPerPage: Int = 5,
        orderBy: String = "date"
    ): URL {
        val params = "$QUERY_PARAMETER_COUNT_PER_PAGE=$numOfPostsPerPage"
        params.plus("&$QUERY_PARAMETER_POSTS_PAGE=$currentPage")
        params.plus("&$QUERY_PARAMETER_POSTS_BY_CATEGORY=$categoryId")
        params.plus("&$QUERY_PARAMETER_POSTS_ORDER=$orderBy")
        return URL("${BASE_URL}/$PATH_POSTS?$params")
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{categories}
     */
    fun categoryBuilder(): URL {
        return URL("${BASE_URL}/$PATH_CATEGORIES")
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{users}?per_page=30
     */
    fun authorBuilder(
        numOfAuthorsPerPage: Int = 30
    ): URL {
        val params = "$QUERY_PARAMETER_COUNT_PER_PAGE=$numOfAuthorsPerPage"
        return URL("${BASE_URL}/$PATH_USERS?$params")
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/users/{107376512}
     */
    fun authorBuilderById(
        authorId: Int
    ): URL {
        return URL("${BASE_URL}/$PATH_USERS/$authorId")
    }

    companion object {

        private const val SCHEME_AUTHORITY = "https://developersbreach.com"
        private const val APPEND_PATH = "wp-json/wp/v2"
        private val BASE_URL = baseUriBuilder()

        private const val PATH_POSTS = "posts"
        private const val PATH_USERS = "users"
        private const val PATH_CATEGORIES = "categories"

        private const val QUERY_PARAMETER_COUNT_PER_PAGE = "per_page"
        private const val QUERY_PARAMETER_POSTS_PAGE = "page"
        private const val QUERY_PARAMETER_POSTS_BY_CATEGORY = "categories"
        private const val QUERY_PARAMETER_POST_INCLUDE_ID = "include"
        private const val QUERY_PARAMETER_POSTS_ORDER = "orderby"

        /**
         * https://developersbreach.com/wp-json/wp/v2
         */
        private fun baseUriBuilder(): Uri.Builder {
            val baseUri: Uri = Uri.parse(SCHEME_AUTHORITY)
            val uriBuilder: Uri.Builder = baseUri.buildUpon()
            uriBuilder.appendPath(APPEND_PATH)
            return uriBuilder
        }
    }
}