package com.zebrostudio.movied.viewmodel

import com.zebrostudio.movied.viewmodel.Status.*

data class ResourceResult<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResourceResult<T> {
            return ResourceResult(SUCCESS, data, null)
        }

        fun <T> error(msg: String): ResourceResult<T> {
            return ResourceResult(ERROR, null, msg)
        }

        fun <T> loading(data: T? = null): ResourceResult<T> {
            return ResourceResult(LOADING, data, null)
        }
    }
}