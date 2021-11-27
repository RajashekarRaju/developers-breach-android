package com.developerbreach.developerbreach.networkManager

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator

enum class DataState {
    LOADING, DONE, ERROR
}


@BindingAdapter("bindCircularProgressDataState")
fun CircularProgressIndicator.setRecentArticlesDataState(
    dataState: DataState?
) {
    when (dataState) {
        DataState.LOADING -> this.visibility = View.VISIBLE
        DataState.DONE -> this.visibility = View.GONE
        DataState.ERROR -> this.visibility = View.GONE
    }
}


@BindingAdapter("bindLinearProgressDataState")
fun LinearProgressIndicator.setLinearProgressDataState(
    dataState: DataState?
) {
    when (dataState) {
        DataState.LOADING -> this.visibility = View.VISIBLE
        DataState.DONE -> this.visibility = View.GONE
        DataState.ERROR -> this.visibility = View.GONE
    }
}