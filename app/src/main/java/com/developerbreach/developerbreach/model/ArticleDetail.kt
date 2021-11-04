package com.developerbreach.developerbreach.model

data class ArticleDetail(
    val articleId: Int,
    val authorId: Int,
    val name: String,
    val banner: String,
    val postedDate: String,
    val urlLink: String,
    val excerpt: String
)
