package com.developerbreach.developerbreach.utils


object DatabaseProperties {

    const val NAME: String = "articles.db"

    object EntityTable {
        const val FAVORITES = "favorites_table"
    }

    object ColumnFavorites {
        const val ID = "favorite_id"
        const val NAME = "favorite_title"
        const val BANNER = "favorite_banner"
    }
}

object JsonProperty {

    object Objects {
        const val TITLE = "title"
        const val EXCERPT = "excerpt"
        const val AVATAR_URLS = "avatar_urls"
    }

    object Values {
        const val ID = "id"
        const val AUTHOR_ID = "author"
        const val TITLE = "rendered"
        const val POSTED_DATE = "date"
        const val URL_LINK = "link"
        const val EXCERPT = "rendered"
        const val BANNER = "jetpack_featured_media_url"
        const val NAME = "name"
        const val AUTHOR_AVATAR_HIGH_RES = "96"
    }
}

object Preferences {
    const val CONTACT_KEY: String = "Contact"
    const val DEVELOPER_KEY: String = "Developer"
}

const val SCHEME_AUTHORITY = "https://developersbreach.com"
const val APPEND_PATH = "wp-json/wp/v2"

const val APPEND_PATH_USERS = "users"
const val APPEND_PATH_CATEGORIES = "categories"
const val APPEND_PATH_POSTS = "posts"

const val QUERY_PARAMETER_COUNT_PER_PAGE = "per_page"
const val QUERY_PARAMETER_POSTS_PAGE = "page"
const val QUERY_PARAMETER_POSTS_BY_CATEGORY = "categories"
const val QUERY_PARAMETER_POST_INCLUDE_ID = "include"
const val QUERY_PARAMETER_POSTS_ORDER = "orderby"
const val ORDER_BY_DATE = "date"
