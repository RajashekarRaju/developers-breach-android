package com.developerbreach.developerbreach.view.commonWebView

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController


@BindingAdapter("bindCommonWebViewToolBar")
fun Toolbar.setCommonWebViewToolBar(
    viewModel: CommonWebViewViewModel
) {
    this.title = viewModel.toolBarTitle
    this.setNavigationOnClickListener {
        findNavController().navigateUp()
    }
}

@BindingAdapter("bindCommonWebViewClient", "bindCommonWebViewUrl")
fun WebView.setCommonWebViewClient(
    progressBar: ProgressBar,
    viewModel: CommonWebViewViewModel
) {
    this.loadUrl(viewModel.webUrl)
    this.webViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}
