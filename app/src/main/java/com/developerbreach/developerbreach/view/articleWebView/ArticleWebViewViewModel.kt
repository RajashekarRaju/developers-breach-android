package com.developerbreach.developerbreach.view.articleWebView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.developerbreach.developerbreach.model.Article


class ArticleWebViewViewModel(
    application: Application,
    article: Article
) : AndroidViewModel(application) {

    /**
     * @return returns internally exposed selected article data of type liveData, any external
     * objects can use this return value.
     */
    var selectedWebViewArticle = article
}