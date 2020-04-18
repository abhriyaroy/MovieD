package com.zebrostudio.movied.utils

import android.content.Context
import android.graphics.Typeface


class TypeFactory(context: Context) {
    private val A_REGULAR = ""
    var ambleRegular: Typeface

    init {
        ambleRegular = Typeface.createFromAsset(context.assets, A_REGULAR)
    }
}