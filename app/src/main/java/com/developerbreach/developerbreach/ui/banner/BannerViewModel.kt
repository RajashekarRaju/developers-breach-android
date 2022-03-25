package com.developerbreach.developerbreach.ui.banner

import android.app.Application
import androidx.lifecycle.*


class BannerViewModel(
    application: Application,
    bannerUrlString: String?
) : AndroidViewModel(application) {

    private val _bannerUrlString = MutableLiveData<String?>()
    val bannerUrl: LiveData<String?>
        get() = _bannerUrlString

    init {
        _bannerUrlString.value = bannerUrlString
    }

    companion object {

        fun provideFactory(
            application: Application,
            bannerUrlString: String?,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(BannerViewModel::class.java)) {
                        return BannerViewModel(application, bannerUrlString) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for BannerViewModel class")
                }
            }
        }
    }
}