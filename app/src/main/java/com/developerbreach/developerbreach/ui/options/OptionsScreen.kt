package com.developerbreach.developerbreach.ui.options

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.local.LocalRepository

@Composable
fun OptionsScreen(
    navigateUp: () -> Unit,
    repository: LocalRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = OptionsViewModel.provideFactory(application, repository),
        modelClass = OptionsViewModel::class.java
    )
}