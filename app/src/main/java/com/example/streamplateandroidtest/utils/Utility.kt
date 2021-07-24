package com.example.streamplateandroidtest.utils

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int{
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    var screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / columnWidthDp + 0.5).roundToInt()
}

fun pixelsToDp(px: Float, context: Context): Float{
    return px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}