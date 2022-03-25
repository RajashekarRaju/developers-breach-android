package com.developerbreach.developerbreach.ui.favorites

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.database.DatabaseRepository


@Composable
fun FavoritesScreen(
    navigateToSelectedArticle: (Int) -> Unit,
    navigateUp: () -> Unit,
    repository: DatabaseRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = FavoritesViewModel.provideFactory(application, repository),
        modelClass = FavoritesViewModel::class.java
    )
}