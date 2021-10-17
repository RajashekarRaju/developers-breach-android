package com.developerbreach.developerbreach.utils


object DatabaseProperties {

    const val NAME: String = "articles.db"

    object EntityTable {
        // const val ARTICLE = "articles_table"
        const val FAVORITES = "favorites_table"
    }

    object ColumnFavorites {
        const val BASE_ID = "favorite_base_id"
        const val ID = "favorite_id"
        const val AUTHOR_ID = "author_id"
        const val NAME = "favorite_title"
        const val BANNER = "favorite_banner"
        const val POSTED_DATE = "favorite_posted_date"
        const val URL_LINK = "favorite_url_link"
        const val EXCERPT = "favorite_excerpt"
    }

    // object ColumnArticles {
    //     const val BASE_ID = "article_base_id"
    //     const val ID = "article_id"
    //     const val NAME = "article_title"
    //     const val BANNER = "article_banner"
    //     const val POSTED_DATE = "article_posted_date"
    //     const val URL_LINK = "article_url_link"
    //     const val EXCERPT = "article_excerpt"
    // }
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
        const val DESCRIPTION = "description"
        const val POST_URL = "link"
        const val AUTHOR_AVATAR_HIGH_RES = "96"
    }
}

object Preferences {
    const val CONTACT_KEY: String = "Contact"
    const val DEVELOPER_KEY: String = "Developer"
}

const val SCHEME_AUTHORITY = "https://developersbreach.com"
const val APPEND_PATH = "wp-json/wp/v2"
const val APPEND_USERS_PATH = "users"

// ?per_page=21
const val APPEND_ENDPOINT_POSTS = "posts"
const val QUERY_PARAMETER_POSTS_PER_PAGE = "per_page"
const val QUERY_PARAMETER_POSTS_BY_CATEGORY = "categories"
const val QUERY_PARAMETER_POST_INCLUDE_ID = "include"

// Get multiple articles by id's
// const val ARTICLES_ID = "https://developersbreach.com/wp-json/wp/v2/posts?include=8432,7787"

// Get categories list
// const val CATEGORIES = "https://developersbreach.com/wp-json/wp/v2/categories"
