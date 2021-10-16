package com.developerbreach.developerbreach.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.utils.DatabaseProperties.ColumnFavorites

@Entity(tableName = DatabaseProperties.EntityTable.FAVORITES)
data class FavoritesEntity constructor(

    @ColumnInfo(name = ColumnFavorites.BASE_ID)
    val id: Int,

    @PrimaryKey
    @ColumnInfo(name = ColumnFavorites.ID)
    val articleId: Int,

    @ColumnInfo(name = ColumnFavorites.AUTHOR_ID)
    val authorId: Int,

    @ColumnInfo(name = ColumnFavorites.NAME)
    val name: String,

    @ColumnInfo(name = ColumnFavorites.BANNER)
    val banner: String,

    @ColumnInfo(name = ColumnFavorites.POSTED_DATE)
    val postedDate: String,

    @ColumnInfo(name = ColumnFavorites.URL_LINK)
    val urlLink: String,

    @ColumnInfo(name = ColumnFavorites.EXCERPT)
    val excerpt: String
)

fun List<FavoritesEntity>.asDomainModel(): List<Article> {
    return map {
        Article(
            id = it.id,
            articleId = it.articleId,
            authorId = it.authorId,
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
        authorId = this.authorId,
        name = this.name,
        banner = this.banner,
        postedDate = this.postedDate,
        urlLink = this.urlLink,
        excerpt = this.excerpt
    )
}