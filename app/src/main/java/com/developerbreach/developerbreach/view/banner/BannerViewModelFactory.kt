package com.developerbreach.developerbreach.view.banner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * A AndroidViewModelFactory for creating a instance of [BannerViewModel]
 * AndroidViewModel for fragment class [BannerFragment] with a constructor.
 */
class BannerViewModelFactory
/**
 * Creates a [ViewModelProvider.AndroidViewModelFactory]
 *
 * @param application parameter to pass in [AndroidViewModel]
 * @param bannerUrlString user selected Article's url to pass in [AndroidViewModel]
 */
internal constructor(
        private val application: Application,
        private val bannerUrlString: String
) : ViewModelProvider.AndroidViewModelFactory(application) {

    /**
     * @param modelClass to check if our [BannerViewModel] model class is assignable.
     * @param <T>        type of generic class
     * @return returns the ViewModel class with passing parameters if instance is created.
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BannerViewModel::class.java)) {
            return BannerViewModel(application, bannerUrlString) as T
        }
        throw IllegalArgumentException("Cannot create Instance for BannerViewModel class")
    }

}