package com.developerbreach.developerbreach.repository.network

import android.net.Uri
import com.developerbreach.developerbreach.utils.APPEND_ENDPOINT_POSTS
import com.developerbreach.developerbreach.utils.APPEND_PATH
import com.developerbreach.developerbreach.utils.QUERY_PARAMETER_POSTS_PER_PAGE
import com.developerbreach.developerbreach.utils.SCHEME_AUTHORITY

/**
 * Builds Uri used to fetch data from the server.
 * @return The String to use to query the data from the server.
 *
 * https://developersbreach.com/wp-json/wp/v2/posts
 */
object QueryBuilder {

    /**
     * https://developersbreach.com/wp-json/wp/v2/{posts}
     * [numberOfPostsPerPage] updates list with new content.
     */
    fun articleBuilder(numberOfPostsPerPage: Int = 5): String {
        val baseUri: Uri = Uri.parse(SCHEME_AUTHORITY)
        val uriBuilder: Uri.Builder = baseUri.buildUpon()
        uriBuilder.appendPath(APPEND_PATH)
        uriBuilder.appendPath(APPEND_ENDPOINT_POSTS)
        uriBuilder.appendQueryParameter(QUERY_PARAMETER_POSTS_PER_PAGE, "$numberOfPostsPerPage")
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{categories}
     */
    fun categoryBuilder(): String {
        val baseUri: Uri = Uri.parse(SCHEME_AUTHORITY)
        val uriBuilder: Uri.Builder = baseUri.buildUpon()
        uriBuilder.appendPath(APPEND_PATH)
        uriBuilder.appendPath("categories")
        return uriBuilder.build().toString()
    }

    /**
     * https://developersbreach.com/wp-json/wp/v2/{users}
     */
    fun authorBuilder(): String {
        val baseUri: Uri = Uri.parse(SCHEME_AUTHORITY)
        val uriBuilder: Uri.Builder = baseUri.buildUpon()
        uriBuilder.appendPath(APPEND_PATH)
        uriBuilder.appendPath("users")
        return uriBuilder.build().toString()
    }
}