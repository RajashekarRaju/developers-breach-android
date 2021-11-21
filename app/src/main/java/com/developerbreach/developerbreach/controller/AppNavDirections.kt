package com.developerbreach.developerbreach.controller

import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.view.detail.ArticleDetailFragmentDirections
import com.developerbreach.developerbreach.view.favorites.FavoritesFragmentDirections
import com.developerbreach.developerbreach.view.home.HomeFragmentDirections
import com.developerbreach.developerbreach.view.intro.IntroFragmentDirections
import com.developerbreach.developerbreach.view.list.ArticleListFragmentDirections
import com.developerbreach.developerbreach.view.options.OptionsFragmentDirections
import com.developerbreach.developerbreach.view.search.SearchFragmentDirections
import com.developerbreach.developerbreach.view.settings.SettingsFragmentDirections
import com.google.android.material.card.MaterialCardView


class AppNavDirections(
    private val navController: NavController,
) {

    /** IntroFragment **/

    fun introToHome() {
        navController.navigate(IntroFragmentDirections.introToHome())
    }

    /** HomeFragment **/

    fun homeToSearch() {
        navController.navigate(HomeFragmentDirections.homeToSearch())
    }

    fun homeToDetail(articleId: Int, view: MaterialCardView) {
        navController.navigate(
            HomeFragmentDirections.homeToDetail(articleId),
            FragmentNavigatorExtras(view to articleId.toString())
        )
    }

    fun homeToOptions() {
        navController.navigate(HomeFragmentDirections.homeToOptions())
    }

    fun homeToIntro() {
        navController.navigate(R.id.introFragment)
    }

    fun homeToArticleList() {
        navController.navigate(HomeFragmentDirections.homeToArticleList())
    }

    /** SearchFragment **/

    fun searchToDetail(articleId: Int, view: TextView) {
        navController.navigate(
            SearchFragmentDirections.searchToDetail(articleId),
            FragmentNavigatorExtras(view to articleId.toString())
        )
    }

    /** ArticleListFragment **/

    fun articlesListToDetail(articleId: Int, view: MaterialCardView) {
        navController.navigate(
            ArticleListFragmentDirections.articleListToDetail(articleId),
            FragmentNavigatorExtras(view to articleId.toString())
        )
    }

    /** FavoritesFragment **/

    fun favoritesToDetail(articleId: Int, view: MaterialCardView) {
        navController.navigate(
            FavoritesFragmentDirections.favoritesToDetail(articleId),
            FragmentNavigatorExtras(view to articleId.toString())
        )
    }

    /** DetailFragment **/

    fun detailToArticleWebView(articleUrlLink: String?) {
        navController.navigate(ArticleDetailFragmentDirections.detailToArticleWebView(articleUrlLink))
    }

    fun detailToBanner(bannerUrl: String?) {
        navController.navigate(ArticleDetailFragmentDirections.detailToBanner(bannerUrl))
    }

    /** SettingsFragment **/

    fun settingsToCommonWebView(webUrl: String) {
        navController.navigate(SettingsFragmentDirections.settingsToCommonWebView(webUrl))
    }

    /** OptionsFragment **/

    fun optionsToAuthors() {
        navController.navigate(OptionsFragmentDirections.optionsToAuthors())
    }

    fun optionsToFavorites() {
        navController.navigate(OptionsFragmentDirections.optionsToFavorites())
    }

    fun optionsToSearch() {
        navController.navigate(OptionsFragmentDirections.optionsToSearch())
    }

    fun optionsToSettings() {
        navController.navigate(OptionsFragmentDirections.optionsToSettings())
    }

    fun optionsToIntro() {
        navController.navigate(OptionsFragmentDirections.optionsToIntro())
    }

    /** NetworkFragment **/

    fun toNetworkFragment() {
        navController.navigate(R.id.networkFragment)
    }
}

