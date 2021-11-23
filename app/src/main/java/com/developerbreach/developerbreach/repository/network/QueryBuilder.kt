package com.developerbreach.developerbreach.repository.network

import android.net.Uri
import com.developerbreach.developerbreach.utils.*

/**
 * Builds Uri used to fetch data from the server.
 * @return The String to use to query the data from the server.
 *
 * https://developersbreach.com/wp-json/wp/v2/posts
 */
object QueryBuilder {

    private const val NUMBER_OF_POSTS_PER_PAGE = 5.toString()

    /**
     * https://developersbreach.com/wp-json/wp/v2/posts?per_page=5
     */
    fun articleBuilder(
        numberOfPosts: Int
    ): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_POSTS)
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_PER_PAGE, numberOfPosts.toString())
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/posts?include={10920}
     * [articleId] updates list with new content.
     */
    fun articleByIdBuilder(
        articleId: Int
    ): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_POSTS)
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POST_INCLUDE_ID, articleId.toString())
        return uriBuilder.build().toString()
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
        postsPage: Int
    ): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_POSTS)
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_PER_PAGE, NUMBER_OF_POSTS_PER_PAGE)
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_PAGE, postsPage.toString())
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_BY_CATEGORY, categoryId.toString())
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_ORDER, ORDER_BY_DATE)
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{categories}
     */
    fun categoryBuilder(): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_CATEGORIES)
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{users}
     */
    fun authorBuilder(): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_USERS)
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/users/{107376512}
     */
    fun authorBuilderById(
        authorId: Int
    ): String {
        val uriBuilder: Uri.Builder = baseUriBuilder()
        uriBuilder.appendPath(APPEND_PATH_USERS)
        uriBuilder.appendPath(authorId.toString())
        return uriBuilder.build().toString()
    }

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