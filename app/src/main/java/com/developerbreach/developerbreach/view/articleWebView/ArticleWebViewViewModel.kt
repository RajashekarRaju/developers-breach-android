package com.developerbreach.developerbreach.view.articleWebView

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class ArticleWebViewViewModel(
    application: Application,
    val articleUrlLink: String
) : AndroidViewModel(application)