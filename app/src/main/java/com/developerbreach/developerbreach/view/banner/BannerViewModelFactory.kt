package com.developerbreach.developerbreach.view.banner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class BannerViewModelFactory(
    private val application: Application,
    private val bannerUrlString: String?
) : ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BannerViewModel::class.java)) {
            return BannerViewModel(application, bannerUrlString) as T
        }
        throw IllegalArgumentException("Cannot create Instance for BannerViewModel class")
    }
}