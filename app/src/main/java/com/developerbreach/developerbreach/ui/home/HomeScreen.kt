package com.developerbreach.developerbreach.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.components.LoadNetworkImage
import com.developerbreach.developerbreach.components.ShowProgressIndicator
import com.developerbreach.developerbreach.model.Article
import com.developerbreach.developerbreach.navigation.AppActions
import com.developerbreach.developerbreach.repository.local.LocalRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    networkRepository: NetworkRepository,
    localRepository: LocalRepository,
    application: DevelopersBreachApp,
    appActions: AppActions
) {
    val viewModel = viewModel(
        factory = HomeViewModel.provideFactory(application, networkRepository, localRepository),
        modelClass = HomeViewModel::class.java
    )

    LaunchedEffect(Unit) {
        if (viewModel.navigateToIntro) {
            appActions.homeToIntro
        }
    }

    val uiState = viewModel.uiState

    val modalSheetSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = tween(durationMillis = 500),
        skipHalfExpanded = true
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetSheet,
        sheetElevation = 24.dp,
        scrimColor = MaterialTheme.colors.background.copy(0.25f),
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            OptionsModalSheetContent(modalSheetSheet, viewModel, appActions)
        },
    ) {
        Scaffold(
            topBar = { HomeTopBar(appActions.homeToSearch) },
            bottomBar = { HomeBottomBar(modalSheetSheet) },
            backgroundColor = MaterialTheme.colors.background
        ) {
            Box {
                // Show progress while data is loading
                ShowProgressIndicator(isLoadingData = uiState.isFetchingArticles)

                HomeScreenContent(
                    uiState,
                    appActions.homeToArticlesCategory,
                    appActions.navigateToSelectedArticle
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    homeToArticlesCategory: () -> Unit,
    navigateToSelectedArticle: (Int) -> Unit
) {
    val articles = uiState.articleList

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp, end = 16.dp, top = 0.dp, bottom = 76.dp
        ),
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.recent_posts_tag),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                )

                Text(
                    text = stringResource(id = R.string.view_all_tag),
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.clickable {
                        homeToArticlesCategory()
                    }
                )
            }
        }

        items(articles) { article ->
            ItemArticle(article, navigateToSelectedArticle)
        }
    }
}

@Composable
fun ItemArticle(
    article: Article,
    navigateToSelectedArticle: (Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navigateToSelectedArticle(article.articleId)
            }
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