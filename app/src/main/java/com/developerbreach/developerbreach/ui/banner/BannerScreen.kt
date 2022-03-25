package com.developerbreach.developerbreach.ui.banner

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp


@Composable
fun BannerScreen(
    navigateUp: () -> Unit,
    bannerUrl: String?,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = BannerViewModel.provideFactory(application, bannerUrl),
        modelClass = BannerViewModel::class.java
    )
}