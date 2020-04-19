package com.zebrostudio.movied.utils

import com.google.gson.Gson

class Serializer {
    val gson by lazy {
        Gson()
    }

    fun getStringFromObj(input: Any): String {
        return gson.toJson(input)
    }

    fun <T> getObjFromString(input: String, classType: Class<T>): T {
        return gson.fromJson(input, classType)
    }
}