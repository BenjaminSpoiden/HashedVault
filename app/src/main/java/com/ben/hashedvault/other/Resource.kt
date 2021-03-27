package com.ben.hashedvault.other

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status { SUCCESS, FAILURE, LOADING }

    companion object {
        fun <T> onSuccess(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)
        fun <T> onFailure(message: String?, data: T?) = Resource(Status.FAILURE, data, message)
        fun <T> onLoading(data: T?) = Resource(Status.LOADING, data, null)
    }
}