package com.developerbreach.developerbreach.ui.detail
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
//
//class ArticleDetailViewModelFactory(
//    private val application: Application,
//    private val articleId: Int?
//) : AndroidViewModelFactory(application) {
//
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
//            return ArticleDetailViewModel(application, articleId, databaseRepository, articleId) as T
//        }
//        throw IllegalArgumentException("Cannot create Instance for ArticleDetailViewModel class")
//    }
//}