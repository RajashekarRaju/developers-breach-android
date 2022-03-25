package com.developerbreach.developerbreach.ui.commonWebView
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//class CommonWebViewViewModelFactory(
//    private val application: Application,
//    private val urlString: String?
//) :
//    ViewModelProvider.Factory {
//
//    @Suppress("unchecked_cast")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CommonWebViewViewModel::class.java)) {
//            return CommonWebViewViewModel(application, urlString) as T
//        }
//        throw IllegalArgumentException("Cannot create instance for class WebViewViewModel")
//    }
//}