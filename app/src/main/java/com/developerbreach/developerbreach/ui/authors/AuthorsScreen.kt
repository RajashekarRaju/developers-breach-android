package com.developerbreach.developerbreach.ui.authors

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@Composable
fun AuthorsScreen(
    navigateUp: () -> Unit,
    repository: NetworkRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = AuthorsViewModel.provideFactory(application, repository),
        modelClass = AuthorsViewModel::class.java
    )
}