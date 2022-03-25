package com.developerbreach.developerbreach.ui.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@Composable
fun HomeScreen(
    homeToSearch: Unit,
    navigateToSelectedArticle: (Int) -> Unit,
    repository: NetworkRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = HomeViewModel.provideFactory(application, repository),
        modelClass = HomeViewModel::class.java
    )
}
