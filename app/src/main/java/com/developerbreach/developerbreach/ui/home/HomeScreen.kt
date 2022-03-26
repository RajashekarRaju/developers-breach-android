package com.developerbreach.developerbreach.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.components.LoadNetworkImage
import com.developerbreach.developerbreach.components.ShowProgressIndicator
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeToSearch: () -> Unit,
    navigateToSelectedArticle: (Int) -> Unit,
    repository: NetworkRepository,
    application: DevelopersBreachApp
) {
    val viewModel = viewModel(
        factory = HomeViewModel.provideFactory(application, repository),
        modelClass = HomeViewModel::class.java
    )

    val uiState = viewModel.uiState

    val modalSheetSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = tween(durationMillis = 500),
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetContent = { OptionsModalSheetContent(modalSheetSheet) },
        sheetState = modalSheetSheet,
        sheetElevation = 24.dp,
        scrimColor = MaterialTheme.colors.background.copy(0.25f),
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Scaffold(
            topBar = { HomeTopBar(homeToSearch) },
            bottomBar = { HomeBottomBar(modalSheetSheet) },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Box {
                // Show progress while data is loading
                ShowProgressIndicator(isLoadingData = uiState.isFetchingArticles)

                HomeScreenContent(uiState)
            }
        }
    }
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState
) {
    val articles = uiState.articleList

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp, end = 16.dp, top = 16.dp, bottom = 76.dp
        ),
    ) {
        items(articles) { article ->
            ItemArticle(article)
        }
    }
}

@Composable
fun ItemArticle(
    article: Article
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LoadNetworkImage(
                imageUrl = article.banner,
                contentDescription = "",
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .aspectRatio(ratio = 1.75f)
            )

            Text(
                text = article.name,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}