package com.developerbreach.developerbreach.controller

import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.NavDestination
import com.developerbreach.developerbreach.R


fun MainActivity.onDestinationChanged(
    destination: NavDestination,
) {
    when (destination.id) {

        R.id.introFragment -> {
            navigateUpOnBackPress()
            setSystemUIColor(R.color.colorLowWave)
        }

        R.id.homeFragment -> {
            finishActivityOnBackPress()
            setSystemUIColor(R.color.colorBackground)
        }

        R.id.articleDetailFragment -> {
            navigateUpOnBackPress()
            setSystemUIColor(R.color.surfaceColor)
        }

        R.id.searchFragment, R.id.articleWebViewFragment, R.id.bannerFragment,
        R.id.commonWebViewFragment, R.id.favoritesFragment, R.id.authorsFragment,
        R.id.articleListFragment, R.id.settingsFragment, R.id.optionsFragment -> {
            navigateUpOnBackPress()
        }
    }
}

private fun MainActivity.setSystemUIColor(
    navigationBarColor: Int
) {
    window.navigationBarColor = ContextCompat.getColor(applicationContext, navigationBarColor)
}

private fun MainActivity.finishActivityOnBackPress() {
    onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    })
}

private fun MainActivity.navigateUpOnBackPress() {
    val activity = this
    onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity.navController.navigateUp()
        }
    })
}
