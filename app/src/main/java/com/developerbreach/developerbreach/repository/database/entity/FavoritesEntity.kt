package com.developerbreach.developerbreach.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.utils.DatabaseProperties.ColumnFavorites

@Entity(tableName = DatabaseProperties.EntityTable.FAVORITES)
data class FavoritesEntity constructor(

    @PrimaryKey
    @ColumnInfo(name = ColumnFavorites.ID)
    val articleId: Int,

    @ColumnInfo(name = ColumnFavorites.NAME)
    val name: String,

    @ColumnInfo(name = ColumnFavorites.BANNER)
    val banner: String
)

fun List<FavoritesEntity>.asDomainModel(): List<Article> {
    return map {
        Article(
            articleId = it.articleId,
            name = it.name,
            banner = it.banner
        )
    }
}

fun Article.asDatabaseModel(): FavoritesEntity {
    return FavoritesEntity(
        articleId = this.articleId,
        name = this.name,
        banner = this.banner
    )
}