package com.developerbreach.developerbreach.view.controller

import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.utils.*

fun MainActivity.onDestinationChanged(
    destination: NavDestination,
    navController: NavController
) {

    val activity = this

    // Check for destination ArticleDetailFragment, if true hide the navigation view.
    when (destination.id) {

        R.id.introFragment -> {
            hideStatusBar(activity)
            navigateUpOnBackPress(activity, navController)
            setSystemUIColor(R.color.colorLowWave)
        }

        R.id.homeFragment -> {
            showStatusBar(activity)
            finishActivityOnBackPress(activity)
            setSystemUIColor(R.color.colorBackground)
        }

        R.id.bannerFragment, R.id.optionsFragment -> {
            hideStatusBar(activity)
            navigateUpOnBackPress(activity, navController)
        }

        R.id.searchFragment, R.id.articleDetailFragment, R.id.articleWebViewFragment,
        R.id.commonWebViewFragment, R.id.favoritesFragment, R.id.authorsFragment,
        R.id.articleListFragment -> {
            showStatusBar(activity)
            navigateUpOnBackPress(activity, navController)
        }
    }
}

private fun MainActivity.setSystemUIColor(navigationBarColor: Int) {
    val context = this.applicationContext
    // window.statusBarColor = ContextCompat.getColor(applicationContext, statusBarColor)
    this.window.navigationBarColor = ContextCompat.getColor(context, navigationBarColor)
}
