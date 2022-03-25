package com.developerbreach.developerbreach.ui.commonWebView
//
//import android.graphics.Bitmap
//import android.view.View
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.databinding.BindingAdapter
//import com.google.android.material.progressindicator.LinearProgressIndicator
//
//
//@BindingAdapter("bindCommonWebViewClient", "bindCommonWebViewUrl")
//fun WebView.setCommonWebViewClient(
//    progressBar: LinearProgressIndicator,
//    viewModel: CommonWebViewViewModel
//) {
//    this.loadUrl(viewModel.webUrl)
//    this.webViewClient = object : WebViewClient() {
//        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//            super.onPageStarted(view, url, favicon)
//            progressBar.visibility = View.VISIBLE
//        }
//
//        override fun onPageFinished(view: WebView?, url: String?) {
//            super.onPageFinished(view, url)
//            progressBar.visibility = View.GONE
//        }
//    }
//}
