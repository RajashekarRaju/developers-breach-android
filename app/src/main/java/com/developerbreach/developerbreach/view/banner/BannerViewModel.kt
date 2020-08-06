package com.developerbreach.developerbreach.view.banner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


internal class BannerViewModel(
        application: Application,
        bannerUrlString: String
) : AndroidViewModel(application) {

    /**
     * @return returns internally exposed selected article data of type liveData, any external
     * objects can use this return value.
     */
    private val _bannerUrlString = MutableLiveData<String>()
    val bannerUrl: LiveData<String>
        get() = _bannerUrlString

    init {
        _bannerUrlString.value = bannerUrlString
    }
}