package com.zebrostudio.movied.viewmodel

import com.zebrostudio.movied.viewmodel.Status.*

data class Result<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Result<T> {
            return Result(ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(LOADING, data, null)
        }
    }
}