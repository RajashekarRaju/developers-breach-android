package com.developerbreach.developerbreach.view.controller

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.utils.finishActivityOnBackPress
import com.developerbreach.developerbreach.utils.hideStatusBar
import com.developerbreach.developerbreach.utils.navigateUpOnBackPress
import com.developerbreach.developerbreach.utils.showStatusBar
import com.google.android.material.bottomnavigation.BottomNavigationView


@BindingAdapter("bindMainNavController", "bindMainActivityReference")
fun BottomNavigationView.setMainNavDestinationController(
        navController: NavController,
        mainActivity: MainActivity
) {
    this.setOnNavigationItemSelectedListener(NavigationListener(navController))

    // Change behaviour of destination view or content based on type of destination is user at.
    navController.addOnDestinationChangedListener { _: NavController,
                                                    destination: NavDestination,
                                                    _: Bundle? ->
        onDestinationChanged(destination, this, mainActivity, navController)
    }
}


private fun onDestinationChanged(
        destination: NavDestination,
        bottomNavigationView: BottomNavigationView,
        mainActivity: MainActivity,
        navController: NavController
) {
    // Check for destination ArticleDetailFragment, if true hide the navigation view.
    when (destination.id) {

        R.id.introFragment, R.id.bannerFragment -> {
            bottomNavigationView.visibility = View.GONE
            hideStatusBar(mainActivity)
            navigateUpOnBackPress(mainActivity, navController)
        }

        R.id.articleListFragment, R.id.searchFragment, R.id.favoritesFragment,
        R.id.settingsFragment -> {
            bottomNavigationView.visibility = View.VISIBLE
            showStatusBar(mainActivity)
            finishActivityOnBackPress(mainActivity)
        }

        R.id.articleDetailFragment, R.id.articleWebViewFragment, R.id.commonWebViewFragment -> {
            bottomNavigationView.visibility = View.GONE
            showStatusBar(mainActivity)
            navigateUpOnBackPress(mainActivity, navController)
        }
    }
}

private class NavigationListener(
        private val navController: NavController
) : BottomNavigationView.OnNavigationItemSelectedListener {

    /**
     * @param item The selected item, each item in our case opens a new fragment.
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.articleListFragment, R.id.searchFragment, R.id.favoritesFragment,
            R.id.settingsFragment -> {
                // Call selected destination on object NavigationUI with selected item
                // and controller to manage the navigation.
                NavigationUI.onNavDestinationSelected(item, navController)
                // Start a selected fragment with simple reveal animations every-time.
                //startBottomCircularEffect(frameLayout)
            }
        }
        return true
    }
}