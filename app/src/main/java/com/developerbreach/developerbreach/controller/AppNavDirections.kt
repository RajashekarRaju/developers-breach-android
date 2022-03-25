package com.developerbreach.developerbreach.controller

//import android.view.View
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.navigation.NavController
//import androidx.navigation.NavDirections
//import com.developerbreach.developerbreach.ui.detail.ArticleDetailFragment
//import com.developerbreach.developerbreach.ui.home.HomeFragment
//import com.developerbreach.developerbreach.ui.list.ArticleListFragment
//import com.developerbreach.developerbreach.ui.favorites.FavoritesFragment
//import com.google.android.material.card.MaterialCardView


//class AppNavDirections(
//    private val navController: NavController
//) {
//
//    private fun navigate(
//        directions: NavDirections
//    ) {
//        navController.navigate(directions)
//    }
//
//    private fun navigateWithExtras(
//        directions: NavDirections,
//        extras: FragmentNavigator.Extras
//    ) {
//        navController.navigate(directions, extras)
//    }
//
//    /** IntroFragment */
//    fun introToHome() = navigate(IntroFragmentDirections.introToHome())
//
//    /** HomeFragment */
//    fun homeToSearch() = navigate(HomeFragmentDirections.homeToSearch())
//    fun homeToOptions() = navigate(OptionsFragmentDirections.toOptionsFragmentGlobal())
//    fun homeToIntro() = navigate(IntroFragmentDirections.toIntroFragmentGlobal())
//    fun homeToArticleList() = navigate(HomeFragmentDirections.homeToArticleList())
//
//    /** OptionsFragment */
//    fun optionsToAuthors() = navigate(OptionsFragmentDirections.optionsToAuthors())
//    fun optionsToFavorites() = navigate(OptionsFragmentDirections.optionsToFavorites())
//    fun optionsToSearch() = navigate(OptionsFragmentDirections.optionsToSearch())
//    fun optionsToSettings() = navigate(OptionsFragmentDirections.optionsToSettings())
//    fun optionsToIntro() = navigate(IntroFragmentDirections.toIntroFragmentGlobal())
//
//    /** NetworkFragment */
//    fun toNetworkFragment() = navigate(NetworkFragmentDirections.toNetworkFragmentGlobal())
//
//    /** SearchFragment */
//    fun searchToDetail(
//        articleId: Int,
//        view: TextView
//    ) {
//        navigateWithExtras(
//            ArticleDetailFragmentDirections.toDetailFragmentGlobal(articleId),
//            FragmentNavigatorExtras(view to articleId.toString())
//        )
//    }
//
//    /**
//     * [HomeFragment] [FavoritesFragment] [ArticleListFragment]
//     * to
//     * [ArticleDetailFragment]
//     */
//    fun fragmentsToDetailFragment(
//        articleId: Int,
//        view: MaterialCardView
//    ) {
//        navigateWithExtras(
//            ArticleDetailFragmentDirections.toDetailFragmentGlobal(articleId),
//            FragmentNavigatorExtras(view to articleId.toString())
//        )
//    }
//
//    /** DetailFragment */
//    fun detailToArticleWebView(
//        articleUrlLink: String?
//    ) {
//        navigate(ArticleDetailFragmentDirections.detailToArticleWebView(articleUrlLink))
//    }
//
//    fun detailToBanner(
//        bannerUrl: String?,
//        view: ImageView
//    ) {
//        navigateWithExtras(
//            ArticleDetailFragmentDirections.detailToBanner(bannerUrl),
//            FragmentNavigatorExtras(view as View to bannerUrl.toString())
//        )
//    }
//
//    /** SettingsFragment */
//    fun settingsToCommonWebView(
//        webUrl: String
//    ) {
//        navigate(SettingsFragmentDirections.settingsToCommonWebView(webUrl))
//    }
//}