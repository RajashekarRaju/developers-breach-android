package com.developerbreach.developerbreach.navigation

import android.app.Activity
import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developerbreach.developerbreach.DevelopersBreachApp
import com.developerbreach.developerbreach.controller.MainActivity
import com.developerbreach.developerbreach.ui.articleWebView.ArticleWebViewScreen
import com.developerbreach.developerbreach.ui.authors.AuthorsScreen
import com.developerbreach.developerbreach.ui.banner.BannerScreen
import com.developerbreach.developerbreach.ui.category.CategoryScreen
import com.developerbreach.developerbreach.ui.commonWebView.CommonWebViewScreen
import com.developerbreach.developerbreach.ui.detail.ArticleDetailsScreen
import com.developerbreach.developerbreach.ui.favorites.FavoritesScreen
import com.developerbreach.developerbreach.ui.home.HomeScreen
import com.developerbreach.developerbreach.ui.intro.IntroScreen
import com.developerbreach.developerbreach.ui.offline.OfflineScreen
import com.developerbreach.developerbreach.ui.options.OptionsScreen
import com.developerbreach.developerbreach.ui.search.SearchScreen
import com.developerbreach.developerbreach.ui.settings.SettingsScreen

@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.HOME_ROUTE,
    routes: AppDestinations = AppDestinations,
    activity: Activity = MainActivity()
) {
    // Create a NavHostController to handle navigation.
    val navController = rememberNavController()
    val actions = remember(navController) {
        AppActions(navController, routes)
    }

    // We need Application context and type cast to ComposeActorsApp so that we can access single
    // instance for the AppRepository class.
    val application: Application = LocalContext.current.applicationContext as Application

    // Repository is then passed to all ViewModels with factories.
    val developersBreachApp: DevelopersBreachApp = application as DevelopersBreachApp
    val databaseRepository = developersBreachApp.databaseRepository
    val networkRepository = developersBreachApp.networkRepository
    val localRepository = developersBreachApp.localRepository

    // Composables declared in NavHost can be controlled within by NavController.
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        /** Intro **/
        composable(
            route = AppDestinations.INTRO_ROUTE
        ) {
            BackHandler(enabled = true) {
                activity.finish()
            }

            IntroScreen(
                localRepository,
                application,
                actions.introToHome
            ) {
                navController.popBackStack()
            }
        }

        /** Home **/
        composable(
            route = AppDestinations.HOME_ROUTE
        ) {
            BackHandler(enabled = true) {
                activity.finish()
            }

            HomeScreen(
                networkRepository,
                localRepository,
                application,
                actions
            )
        }

        /** Search **/
        composable(
            route = AppDestinations.SEARCH_ROUTE
        ) {
            SearchScreen(
                actions.navigateToSelectedArticle,
                actions.navigateUp,
                networkRepository,
                application
            )
        }

        /** Favorites **/
        composable(
            route = AppDestinations.FAVORITES_ROUTE
        ) {
            FavoritesScreen(
                actions.navigateToSelectedArticle,
                actions.navigateUp,
                databaseRepository,
                application
            )
        }

        /** Category **/
        composable(
            route = AppDestinations.ARTICLES_CATEGORY_ROUTE
        ) {
            CategoryScreen(
                actions.navigateToSelectedArticle,
                actions.navigateUp,
                networkRepository,
                application
            )
        }

        /** Article Details **/
        composable(
            route = "${AppDestinations.ARTICLE_DETAIL_ROUTE}/{${routes.ARTICLE_DETAIL_ID_KEY}}",
            arguments = listOf(navArgument(routes.ARTICLE_DETAIL_ID_KEY) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val articleId = arguments.getInt(routes.ARTICLE_DETAIL_ID_KEY)
            ArticleDetailsScreen(
                actions.navigateToBanner,
                actions.navigateUp,
                actions.navigateToArticleWebView,
                articleId,
                networkRepository,
                databaseRepository,
                application,
            )
        }

        /** Authors **/
        composable(
            route = AppDestinations.AUTHORS_ROUTE
        ) {
            AuthorsScreen(
                actions.navigateUp,
                networkRepository,
                application
            )
        }

        /** Article WebView **/
        composable(
            route = "${AppDestinations.ARTICLE_WEB_VIEW_ROUTE}/{${routes.ARTICLE_URL_KEY}}",
            arguments = listOf(navArgument(routes.ARTICLE_URL_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val articleUrl = arguments.getString(routes.ARTICLE_URL_KEY)
            ArticleWebViewScreen(
                actions.navigateUp,
                articleUrl,
                application
            )
        }

        /** Banner **/
        composable(
            route = "${AppDestinations.BANNER_ROUTE}/{${routes.BANNER_URL_KEY}}",
            arguments = listOf(navArgument(routes.BANNER_URL_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val bannerUrl = arguments.getString(routes.BANNER_URL_KEY)
            BannerScreen(
                actions.navigateUp,
                bannerUrl,
                application
            )
        }

        /** Common WebView **/
        composable(
            route = "${AppDestinations.COMMON_WEB_VIEW_ROUTE}/{${routes.COMMON_WEB_VIEW_URL_KEY}}",
            arguments = listOf(navArgument(routes.COMMON_WEB_VIEW_URL_KEY) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val webViewUrl = arguments.getString(routes.COMMON_WEB_VIEW_URL_KEY)
            CommonWebViewScreen(
                actions.navigateUp,
                webViewUrl,
                application
            )
        }

        /** Offline **/
        composable(
            route = AppDestinations.OFFLINE_ROUTE
        ) {
            OfflineScreen()
        }

        /** Options **/
        composable(
            route = AppDestinations.OPTIONS_ROUTE
        ) {
            OptionsScreen(
                actions.navigateUp,
                localRepository,
                application
            )
        }

        /** Settings **/
        composable(
            route = AppDestinations.SETTINGS_ROUTE
        ) {
            SettingsScreen()
        }
    }
}
