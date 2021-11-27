package com.developerbreach.developerbreach.utils

import android.app.Activity
import android.util.TypedValue
import android.view.View
import com.google.android.material.snackbar.Snackbar


fun showSnackBar(
    message: String,
    activity: Activity
) {
    val decorView = activity.window.decorView
    val view: View = decorView.findViewById(android.R.id.content)
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun View.convertToDp(
    value: Float
): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        resources.displayMetrics
    ).toInt()
}
