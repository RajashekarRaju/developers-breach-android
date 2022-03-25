package com.developerbreach.developerbreach.ui.articleWebView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp


@Composable
fun ArticleWebViewScreen(
    navigateUp: () -> Unit,
    articleUrl: String?,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = ArticleWebViewViewModel.provideFactory(application, articleUrl),
        modelClass = ArticleWebViewViewModel::class.java
    )
}