package com.developerbreach.developerbreach.model

import com.developerbreach.developerbreach.repository.database.entity.ArticleEntity

data class NetworkArticlesContainer(val articlesNetworks: List<ArticleNetwork>)

data class ArticleNetwork(
        val id: Int,
        val articleId: Int,
        val name: String,
        val banner: String,
        val postedDate: String,
        val urlLink: String,
        val excerpt: String
)

fun NetworkArticlesContainer.asDatabaseModel(): List<ArticleEntity> {
    return articlesNetworks.map {
        ArticleEntity(
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