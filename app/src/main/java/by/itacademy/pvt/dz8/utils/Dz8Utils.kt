package by.itacademy.pvt.dz8.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics

fun Activity.getScreenOrientation(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)

    val width = displayMetrics.widthPixels
    val height = displayMetrics.heightPixels

    var orientation = Configuration.ORIENTATION_UNDEFINED

    if (width == height) {
        orientation = Configuration.ORIENTATION_SQUARE
    } else if (width < height) {
        orientation = Configuration.ORIENTATION_PORTRAIT
    } else {
        orientation = Configuration.ORIENTATION_LANDSCAPE
    }
    return orientation
}

fun isTablet(context: Context): Boolean {
    return (context.resources.configuration.screenLayout and
            Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
}

fun isLandscape(context: Context): Boolean {
    return context.resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
}

fun isPortrait(context: Context): Boolean {
    return context.resources.configuration.orientation ==
            Configuration.ORIENTATION_PORTRAIT
}