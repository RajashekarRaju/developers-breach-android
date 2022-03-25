package com.developerbreach.developerbreach.ui.commonWebView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.developerbreach.developerbreach.R
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import com.developerbreach.developerbreach.ui.favorites.FavoritesViewModel
import com.developerbreach.developerbreach.utils.Preferences

class CommonWebViewViewModel(
    application: Application,
    urlString: String?
) : AndroidViewModel(application) {

    private lateinit var _webUrl: String
    val webUrl: String
        get() = _webUrl

    private lateinit var _toolBarTitle: String
    val toolBarTitle: String
        get() = _toolBarTitle

    init {
        if (urlString == Preferences.CONTACT_KEY) {
            _toolBarTitle = application.getString(R.string.web_view_toolbar_title_contact)
            _webUrl = application.getString(R.string.url_site_contact)
        } else if (urlString == Preferences.DEVELOPER_KEY) {
            _toolBarTitle = application.getString(R.string.web_view_toolbar_title_github)
            _webUrl = application.getString(R.string.url_site_developer)
        }
    }

    companion object {

        fun provideFactory(
            application: Application,
            urlString: String?,
        ): ViewModelProvider.AndroidViewModelFactory {
            return object : ViewModelProvider.AndroidViewModelFactory(application) {
                @Suppress("unchecked_cast")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(CommonWebViewViewModel::class.java)) {
                        return CommonWebViewViewModel(application, urlString) as T
                    }
                    throw IllegalArgumentException("Cannot create Instance for CommonWebViewViewModel class")
                }
            }
        }
    }
}