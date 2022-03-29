package com.developerbreach.developerbreach.ui.detail

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@Composable
fun ArticleDetailsScreen(
    navigateToBanner: (String) -> Unit,
    navigateUp: () -> Unit,
    navigateToArticleWebView: (String) -> Unit,
    articleId: Int,
    networkRepository: NetworkRepository,
    databaseRepository: DatabaseRepository,
    application: DevelopersBreachApp,
) {
    val viewModel = viewModel(
        factory = ArticleDetailViewModel.provideFactory(
            application,
            networkRepository,
            databaseRepository,
            articleId
        ),
        modelClass = ArticleDetailViewModel::class.java
    )
}