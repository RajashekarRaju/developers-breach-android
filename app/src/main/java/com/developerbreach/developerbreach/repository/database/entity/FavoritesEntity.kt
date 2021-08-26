package com.developerbreach.developerbreach.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.*

@Entity(tableName = DATABASE_ENTITY_TABLE_FAVORITES)
data class FavoritesEntity constructor(

    @ColumnInfo(name = COLUMN_FAVORITE_BASE_ID)
    val id: Int,

    @PrimaryKey
    @ColumnInfo(name = COLUMN_FAVORITE_ID)
    val articleId: Int,

    @ColumnInfo(name = COLUMN_FAVORITE_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_FAVORITE_BANNER)
    val banner: String,

    @ColumnInfo(name = COLUMN_FAVORITE_POSTED_DATE)
    val postedDate: String,

    @ColumnInfo(name = COLUMN_FAVORITE_URL_LINK)
    val urlLink: String,

    @ColumnInfo(name = COLUMN_FAVORITE_EXCERPT)
    val excerpt: String
)

fun List<FavoritesEntity>.asDomainModel(): List<Article> {
    return map {
        Article(
            id = it.id,
            articleId = it.articleId,
            name = it.name,
            banner = it.banner,
            postedDate = it.postedDate,
            urlLink = it.urlLink,
            excerpt = it.excerpt
        )
    }
}

fun Article.asDatabaseModel(): FavoritesEntity {
    return FavoritesEntity(
        id = this.id,
        articleId = this.articleId,
        name = this.name,
        banner = this.banner,
        postedDate = this.postedDate,
        urlLink = this.urlLink,
        excerpt = this.excerpt
    )
}