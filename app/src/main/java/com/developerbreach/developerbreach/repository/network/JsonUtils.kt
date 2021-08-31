package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.model.Authors
import com.developerbreach.developerbreach.model.Categories
import com.developerbreach.developerbreach.utils.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList

object JsonRemoteData {

    fun fetchAuthorsJsonData(response: String): List<Authors> {

        val authorsList: MutableList<Authors> = ArrayList()

        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            var authorId = 0
            if (baseJsonObject.has("id")) {
                authorId = baseJsonObject.getInt("id")
            }

            var authorName: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has("name")) {
                authorName = baseJsonObject.getString("name")
            }

            var description: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has("description")) {
                description = baseJsonObject.getString("description")
            }

            var authorPostsLinkUrl: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has("link")) {
                authorPostsLinkUrl = baseJsonObject.getString("link")
            }

            val avatarsJsonObject = baseJsonObject.getJSONObject("avatar_urls")

            var authorAvatarUrl: String = CHECK_WITH_EMPTY_ASSERTION
            if (avatarsJsonObject.has("96")) {
                authorAvatarUrl = avatarsJsonObject.getString("96")
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
            if (baseJsonObject.has("id")) {
                categoryId = baseJsonObject.getInt("id")
            }

            var categoryName: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has("name")) {
                categoryName = baseJsonObject.getString("name")
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

            val jsonObjectTitle = baseJsonObject.getJSONObject(JSON_OBJECT_TITLE)
            val jsonObjectExcerpt = baseJsonObject.getJSONObject(JSON_OBJECT_EXCERPT)

            val id: Int = baseJsonArray.length() - i

            var articleId = 0
            if (baseJsonObject.has(ARTICLE_ID)) {
                articleId = baseJsonObject.getInt(ARTICLE_ID)
            }

            var banner: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has(ARTICLE_BANNER)) {
                banner = baseJsonObject.getString(ARTICLE_BANNER)
            }

            var title: String = CHECK_WITH_EMPTY_ASSERTION
            if (jsonObjectTitle.has(ARTICLE_TITLE)) {
                title = jsonObjectTitle.getString(ARTICLE_TITLE)
            }

            var postedDate: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has(ARTICLE_POSTED_DATE)) {
                postedDate = baseJsonObject.getString(ARTICLE_POSTED_DATE)
            }

            var urlLink: String = CHECK_WITH_EMPTY_ASSERTION
            if (baseJsonObject.has(ARTICLE_URL_LINK)) {
                urlLink = baseJsonObject.getString(ARTICLE_URL_LINK)
            }

            var excerpt: String = CHECK_WITH_EMPTY_ASSERTION
            if (jsonObjectExcerpt.has(ARTICLE_EXCERPT)) {
                excerpt = jsonObjectExcerpt.getString(ARTICLE_EXCERPT)
            }

            val articlesNetwork = Article(
                id,
                articleId,
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

}
