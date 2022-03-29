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
