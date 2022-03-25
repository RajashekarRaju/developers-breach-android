package com.developerbreach.developerbreach.ui.search

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@Composable
fun SearchScreen(
    navigateToSelectedArticle: (Int) -> Unit,
    navigateUp: () -> Unit,
    repository: NetworkRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = SearchViewModel.provideFactory(application, repository),
        modelClass = SearchViewModel::class.java
    )
}