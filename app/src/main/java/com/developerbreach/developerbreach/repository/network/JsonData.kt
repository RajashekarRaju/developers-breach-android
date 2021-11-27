package com.developerbreach.developerbreach.repository.network

import com.developerbreach.developerbreach.model.*
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.utils.JsonProperty.Values
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList


class JsonData {

    private val assertEmptyAssertion: String = ""

    private fun ifHasString(
        jsonObject: JSONObject,
        value: String
    ): String {
        var resultString = assertEmptyAssertion
        if (jsonObject.has(value)) {
            resultString = jsonObject.getString(value)
        }
        return resultString
    }

    private fun ifHasInt(
        jsonObject: JSONObject,
        value: String
    ): Int {
        var resultString = 0
        if (jsonObject.has(value)) {
            resultString = jsonObject.getInt(value)
        }
        return resultString
    }

    private fun getAuthorAvatarUrl(
        jsonObject: JSONObject
    ): String {
        val avatarsJsonObject = jsonObject.getJSONObject(JsonProperty.Objects.AVATAR_URLS)
        return ifHasString(avatarsJsonObject, Values.AUTHOR_AVATAR_HIGH_RES)
    }

    companion object {

        fun fetchAuthorDataById(
            response: String
        ): Pair<String, String> {

            val baseJsonObject = JSONObject(response)
            val authorName: String = ifHasString(baseJsonObject, Values.NAME)
            val authorAvatarUrl: String = JsonData().getAuthorAvatarUrl(baseJsonObject)

            return Pair(authorName, authorAvatarUrl)
        }

        fun fetchAuthorsJsonData(
            response: String
        ): List<Authors> {

            val authorsList: MutableList<Authors> = ArrayList()
            val baseJsonArray = JSONArray(response)

            for (i: Int in 0 until baseJsonArray.length()) {

                val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)
                val authorId = ifHasInt(baseJsonObject, Values.ID)
                val authorName: String = ifHasString(baseJsonObject, Values.NAME)
                val authorAvatarUrl: String = JsonData().getAuthorAvatarUrl(baseJsonObject)

                authorsList.add(
                    Authors(authorId, authorName, authorAvatarUrl)
                )
            }
            return authorsList
        }

        fun fetchCategoriesJsonData(
            response: String
        ): List<Categories> {

            val categoriesList: MutableList<Categories> = ArrayList()
            val baseJsonArray = JSONArray(response)

            for (i: Int in 0 until baseJsonArray.length()) {

                val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)
                val categoryId: Int = ifHasInt(baseJsonObject, Values.ID)
                val categoryName: String = ifHasString(baseJsonObject, Values.NAME)

                categoriesList.add(
                    Categories(categoryId, categoryName)
                )
            }
            return categoriesList
        }

        fun fetchArticleJsonData(
            response: String
        ): List<Article> {

            val articlesNetworkList: MutableList<Article> = ArrayList()
            val baseJsonArray = JSONArray(response)

            for (i: Int in 0 until baseJsonArray.length()) {

                val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)
                val jsonObjectTitle = baseJsonObject.getJSONObject(JsonProperty.Objects.TITLE)
                val articleId = ifHasInt(baseJsonObject, Values.ID)
                val banner: String = ifHasString(baseJsonObject, Values.BANNER)
                val title: String = ifHasString(jsonObjectTitle, Values.TITLE)

                articlesNetworkList.add(
                    Article(articleId, title, banner)
                )
            }
            return articlesNetworkList
        }


        fun fetchArticleJsonDataById(
            response: String
        ): ArticleDetail {

            val baseJsonArray = JSONArray(response)
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(0)

            val jsonObjectTitle = baseJsonObject.getJSONObject(JsonProperty.Objects.TITLE)
            val jsonObjectExcerpt = baseJsonObject.getJSONObject(JsonProperty.Objects.EXCERPT)

            val articleId = ifHasInt(baseJsonObject, Values.ID)
            val authorId = ifHasInt(baseJsonObject, Values.AUTHOR_ID)
            val banner: String = ifHasString(baseJsonObject, Values.BANNER)
            val title: String = ifHasString(jsonObjectTitle, Values.TITLE)
            val postedDate: String = ifHasString(baseJsonObject, Values.POSTED_DATE)
            val urlLink: String = ifHasString(baseJsonObject, Values.URL_LINK)
            val excerpt: String = ifHasString(jsonObjectExcerpt, Values.EXCERPT)

            return ArticleDetail(
                articleId, authorId, title, banner, postedDate, urlLink, excerpt
            )
        }

        fun fetchSearchableArticlesJsonData(
            response: String
        ): List<Search> {

            val articlesNetworkList: MutableList<Search> = ArrayList()
            val baseJsonArray = JSONArray(response)

            for (i: Int in 0 until baseJsonArray.length()) {

                val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)
                val articleId = ifHasInt(baseJsonObject, Values.ID)

                val jsonObjectTitle = baseJsonObject.getJSONObject(JsonProperty.Objects.TITLE)
                val title = ifHasString(jsonObjectTitle, Values.TITLE)

                articlesNetworkList.add(
                    Search(articleId, title)
                )
            }
            return articlesNetworkList
        }

        private fun ifHasString(
            jsonObject: JSONObject,
            value: String
        ) = JsonData().ifHasString(jsonObject, value)

        private fun ifHasInt(
            jsonObject: JSONObject,
            value: String
        ) = JsonData().ifHasInt(jsonObject, value)
    }
}