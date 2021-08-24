package com.developerbreach.developerbreach.view.controller

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.utils.finishActivityOnBackPress
import com.developerbreach.developerbreach.utils.hideStatusBar
import com.developerbreach.developerbreach.utils.navigateUpOnBackPress
import com.developerbreach.developerbreach.utils.showStatusBar


fun onDestinationChanged(
    destination: NavDestination,
    mainActivity: MainActivity,
    navController: NavController
) {
    // Check for destination ArticleDetailFragment, if true hide the navigation view.
    when (destination.id) {

        R.id.introFragment, R.id.bannerFragment -> {
            hideStatusBar(mainActivity)
            navigateUpOnBackPress(mainActivity, navController)
        }

        R.id.articleListFragment, R.id.favoritesFragment,
        R.id.settingsFragment -> {
            showStatusBar(mainActivity)
            finishActivityOnBackPress(mainActivity)
        }

        R.id.searchFragment, R.id.articleDetailFragment, R.id.articleWebViewFragment,
        R.id.commonWebViewFragment -> {
            showStatusBar(mainActivity)
            navigateUpOnBackPress(mainActivity, navController)
        }
    }
}