package com.developerbreach.developerbreach.utils

import android.app.Activity
import android.util.TypedValue
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.google.android.material.snackbar.Snackbar
import java.util.*


fun capitalizeWord(title: String): String {
    val splitTitle = title.lowercase(Locale.ROOT).split(" ".toRegex()).toTypedArray()
    val builder = StringBuilder()
    for (i in splitTitle.indices) {
        val currentWordInTitle = splitTitle[i]
        if (i > 0 && currentWordInTitle.isNotEmpty()) {
            builder.append(" ")
        }
        val capitalTitle = (currentWordInTitle.substring(0, 1).lowercase(Locale.ROOT)
                + currentWordInTitle.substring(1))
        builder.append(capitalTitle)
    }
    return builder.toString()
}

fun showSnackBar(message: String, activity: Activity) {
    val decorView = activity.window.decorView
    val view: View = decorView.findViewById(android.R.id.content)
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun finishActivityOnBackPress(activity: FragmentActivity) {
    activity.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity.finish()
        }
    })
}

fun navigateUpOnBackPress(activity: FragmentActivity, findNavController: NavController) {
    activity.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            findNavController.navigateUp()
        }
    })
}

fun View.convertToDp(value: Float): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, resources.displayMetrics)
        .toInt()
}

// TODO Remove deprecated code
fun showStatusBar(activity: Activity) {
    @Suppress("DEPRECATION")
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}

fun hideStatusBar(activity: Activity) {
    @Suppress("DEPRECATION")
    activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
}
