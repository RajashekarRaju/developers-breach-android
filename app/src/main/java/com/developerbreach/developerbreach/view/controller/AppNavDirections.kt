package com.developerbreach.developerbreach.view.controller

import androidx.navigation.NavController
import com.developerbreach.developerbreach.view.intro.IntroFragmentDirections


class AppNavDirections(
    private val navController: NavController
) {

    fun introToHome() {
        navController.navigate(IntroFragmentDirections.introToHome())
    }
}

