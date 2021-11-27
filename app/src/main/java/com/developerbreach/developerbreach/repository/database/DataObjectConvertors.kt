package com.developerbreach.developerbreach.repository.database

import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.database.entity.FavoritesEntity

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