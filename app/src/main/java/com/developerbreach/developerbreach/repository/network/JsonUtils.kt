package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.model.Search
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.utils.JsonProperty.Values
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

private const val ASSERT_EMPTY_ASSERTION: String = ""

object JsonRemoteData {

    fun fetchAuthorDataById(
        response: String
    ): Pair<String, String> {

        val jsonObject = JSONObject(response)

        var authorName: String = ASSERT_EMPTY_ASSERTION
        if (jsonObject.has(Values.NAME)) {
            authorName = jsonObject.getString(Values.NAME)
        }

        val avatarsJsonObject = jsonObject.getJSONObject(JsonProperty.Objects.AVATAR_URLS)

        var authorAvatarUrl: String = ASSERT_EMPTY_ASSERTION
        if (avatarsJsonObject.has(Values.AUTHOR_AVATAR_HIGH_RES)) {
            authorAvatarUrl = avatarsJsonObject.getString(Values.AUTHOR_AVATAR_HIGH_RES)
        }

        return Pair(authorName, authorAvatarUrl)
    }

    fun fetchAuthorsJsonData(response: String): List<Authors> {

        val authorsList: MutableList<Authors> = ArrayList()

        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            var authorId = 0
            if (baseJsonObject.has(Values.ID)) {
                authorId = baseJsonObject.getInt(Values.ID)
            }

            var authorName: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.NAME)) {
                authorName = baseJsonObject.getString(Values.NAME)
            }

            var description: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.DESCRIPTION)) {
                description = baseJsonObject.getString(Values.DESCRIPTION)
            }

            var authorPostsLinkUrl: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.POST_URL)) {
                authorPostsLinkUrl = baseJsonObject.getString(Values.POST_URL)
            }

            val avatarsJsonObject = baseJsonObject.getJSONObject(JsonProperty.Objects.AVATAR_URLS)

            var authorAvatarUrl: String = ASSERT_EMPTY_ASSERTION
            if (avatarsJsonObject.has(Values.AUTHOR_AVATAR_HIGH_RES)) {
                authorAvatarUrl =
                    avatarsJsonObject.getString(Values.AUTHOR_AVATAR_HIGH_RES)
            }

            authorsList.add(
                Authors(
                    authorId,
                    authorName,
                    description,
                    authorPostsLinkUrl,
                    authorAvatarUrl
                )
            )
        }

        return authorsList
    }

    fun fetchCategoriesJsonData(response: String): List<Categories> {

        // Create a new ArrayList for adding articles into list.
        val categoriesList: MutableList<Categories> = ArrayList()

        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            var categoryId = 0
            if (baseJsonObject.has(Values.ID)) {
                categoryId = baseJsonObject.getInt(Values.ID)
            }

            var categoryName: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.NAME)) {
                categoryName = baseJsonObject.getString(Values.NAME)
            }

            categoriesList.add(Categories(categoryId, categoryName))
        }

        return categoriesList
    }


    fun fetchArticleJsonData(response: String): List<Article> {

        // Create a new ArrayList for adding articles into list.
        val articlesNetworkList: MutableList<Article> = ArrayList()

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        // Create a JSONArray from the json response string.
        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            val jsonObjectTitle = baseJsonObject.getJSONObject(JsonProperty.Objects.TITLE)
            val jsonObjectExcerpt = baseJsonObject.getJSONObject(JsonProperty.Objects.EXCERPT)

            val id: Int = baseJsonArray.length() - i

            var articleId = 0
            if (baseJsonObject.has(Values.ID)) {
                articleId = baseJsonObject.getInt(Values.ID)
            }

            var authorId = 0
            if (baseJsonObject.has(Values.AUTHOR_ID)) {
                authorId = baseJsonObject.getInt(Values.AUTHOR_ID)
            }

            var banner: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.BANNER)) {
                banner = baseJsonObject.getString(Values.BANNER)
            }

            var title: String = ASSERT_EMPTY_ASSERTION
            if (jsonObjectTitle.has(Values.TITLE)) {
                title = jsonObjectTitle.getString(Values.TITLE)
            }

            var postedDate: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.POSTED_DATE)) {
                postedDate = baseJsonObject.getString(Values.POSTED_DATE)
            }

            var urlLink: String = ASSERT_EMPTY_ASSERTION
            if (baseJsonObject.has(Values.URL_LINK)) {
                urlLink = baseJsonObject.getString(Values.URL_LINK)
            }

            var excerpt: String = ASSERT_EMPTY_ASSERTION
            if (jsonObjectExcerpt.has(Values.EXCERPT)) {
                excerpt = jsonObjectExcerpt.getString(Values.EXCERPT)
            }

            val articlesNetwork = Article(
                id,
                articleId,
                authorId,
                title,
                banner,
                postedDate,
                urlLink,
                excerpt
            )
            articlesNetworkList.add(articlesNetwork)
        }

        return articlesNetworkList
    }

    fun fetchSearchableArticlesJsonData(response: String): List<Search> {

        // Create a new ArrayList for adding articles into list.
        val articlesNetworkList: MutableList<Search> = ArrayList()

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        // Create a JSONArray from the json response string.
        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            val jsonObjectTitle = baseJsonObject.getJSONObject(JsonProperty.Objects.TITLE)

            var articleId = 0
            if (baseJsonObject.has(Values.ID)) {
                articleId = baseJsonObject.getInt(Values.ID)
            }

            var title: String = ASSERT_EMPTY_ASSERTION
            if (jsonObjectTitle.has(Values.TITLE)) {
                title = jsonObjectTitle.getString(Values.TITLE)
            }

            val articlesNetwork = Search(articleId, title)
            articlesNetworkList.add(articlesNetwork)
        }

        return articlesNetworkList
    }

}
