package com.developerbreach.developerbreach.ui.commonWebView

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp

@Composable
fun CommonWebViewScreen(
    navigateUp: () -> Unit,
    webViewUrl: String?,
    application: DevelopersBreachApp
) {
    val viewViewModel = viewModel(
        factory = CommonWebViewViewModel.provideFactory(application, webViewUrl),
        modelClass = CommonWebViewViewModel::class.java
    )
}