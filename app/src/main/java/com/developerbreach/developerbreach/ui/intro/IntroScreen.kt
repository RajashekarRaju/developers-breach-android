package com.developerbreach.developerbreach.ui.intro

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.repository.local.LocalRepository

@Composable
fun IntroScreen(
    repository: LocalRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = IntroViewModel.provideFactory(application, repository),
        modelClass = IntroViewModel::class.java
    )
}