package com.developerbreach.developerbreach.view.articleWebView

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator


@SuppressLint("SetJavaScriptEnabled")
@BindingAdapter("bindArticleWebViewClient", "bindArticleWebViewUrl")
fun WebView.setArticleWebViewClient(
    progressBar: LinearProgressIndicator,
    selectedArticleWebUrl: String
) {
    this.loadUrl(selectedArticleWebUrl)
    val webSettings = this.settings
    webSettings.javaScriptEnabled = true
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


@BindingAdapter("bindShareFabListener")
fun FloatingActionButton.setFabClickListener(
    articleUrlLink: String
) {
    this.setOnClickListener {
        // Create new sharable intent.
        val sharingIntent = Intent(Intent.ACTION_SEND)
        // Sett type
        sharingIntent.type = "text/plain"
        // Format the data which we send to other apps.
        val body = """
                $articleUrlLink
                """.trimIndent()
        sharingIntent.putExtra(Intent.EXTRA_TEXT, body)
        // Start the intent.
        this.context.startActivity(sharingIntent)
    }
}