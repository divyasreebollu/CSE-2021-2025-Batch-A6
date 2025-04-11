package com.feesBus.ui.common

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import java.text.SimpleDateFormat

fun Fragment.backPointer(nav: NavController) {
    requireActivity().onBackPressedDispatcher.addCallback(
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                nav.navigateUp()
            }
        }
    )
}

fun Fragment.moveToTask() {
    requireActivity().onBackPressedDispatcher.addCallback(
        onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().moveTaskToBack(true)
            }
        }
    )
}

fun spanned(string: String) =
    HtmlCompat.fromHtml(string, HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)

@SuppressLint("SimpleDateFormat")
val simple = SimpleDateFormat("dd MMMM yyyy\nhh:mm:ss a")

fun dateConvertPoint(string: String, store: (String) -> Unit) {
    val long = string.toLongOrNull()
    store.invoke(
        if (long == null) {
            "No Date"
        } else {
            simple.format(long)
        }
    )
}