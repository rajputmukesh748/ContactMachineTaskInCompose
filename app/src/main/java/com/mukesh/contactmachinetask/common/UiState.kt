package com.mukesh.contactmachinetask.common

/**
 * Handle UI State
 * */
sealed class NetworkState<T>() {

    class Loading<T> : NetworkState<T>()

    class Success<T>(val data: T): NetworkState<T>()

    class Error<T>(val exception: Throwable): NetworkState<T>()

}


data class UiState<T>(
    val isLoading: Boolean? = false,
    val data: T? = null,
    val error: Throwable? = null
)