package com.developerbreach.developerbreach.ui.category

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@Composable
fun CategoryScreen(
    navigateToSelectedArticle: (Int) -> Unit,
    navigateUp: () -> Unit,
    repository: NetworkRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = CategoryViewModel.provideFactory(application, repository),
        modelClass = CategoryViewModel::class.java
    )
}