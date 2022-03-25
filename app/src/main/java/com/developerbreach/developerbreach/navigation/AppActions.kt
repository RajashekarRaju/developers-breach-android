package com.developerbreach.developerbreach.navigation

import androidx.navigation.NavHostController

class AppActions(
    private val navController: NavHostController,
    private val routes: AppDestinations
) {

    val introToHome = navController.navigate(routes.HOME_ROUTE)

    val homeToSearch = navController.navigate(routes.SEARCH_ROUTE)
    val homeToArticlesCategory = navController.navigate(routes.ARTICLES_CATEGORY_ROUTE)
    val navigateToSelectedArticle: (Int) -> Unit = { articleId: Int ->
        navController.navigate("${routes.ARTICLE_DETAIL_ROUTE}/$articleId")
    }

    val articleDetailToWebView = navController.navigate(routes.ARTICLE_WEB_VIEW_ROUTE)
    val articleDetailToBanner = navController.navigate(routes.BANNER_ROUTE)

    val navigateToArticleWebView: (String) -> Unit = { articleUrl: String ->
        navController.navigate("${routes.ARTICLE_WEB_VIEW_ROUTE}/$articleUrl")
    }

    val navigateToSettings = navController.navigate(routes.SETTINGS_ROUTE)

    val navigateToCommonArticleWebView: (String) -> Unit = { webViewUrl: String ->
        navController.navigate("${routes.COMMON_WEB_VIEW_ROUTE}/$webViewUrl")
    }

    val navigateToBanner: (String) -> Unit = { bannerUrl: String ->
        navController.navigate("${routes.BANNER_ROUTE}/$bannerUrl")
    }

    val optionsToAuthors = navController.navigate(routes.AUTHORS_ROUTE)
    val optionsToFavorites = navController.navigate(routes.FAVORITES_ROUTE)
    val optionsToSettings = navController.navigate(routes.SETTINGS_ROUTE)
    val optionsToIntro = navController.navigate(routes.INTRO_ROUTE)

    val navigateToOptions = navController.navigate(routes.OPTIONS_ROUTE)
    val navigateToOffline = navController.navigate(routes.OFFLINE_ROUTE)
    val navigateToIntro = navController.navigate(routes.INTRO_ROUTE)

    // Navigates to previous screen from current screen.
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}