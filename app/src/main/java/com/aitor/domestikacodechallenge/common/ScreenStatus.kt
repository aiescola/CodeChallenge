package com.aitor.domestikacodechallenge.common

sealed class ScreenStatus {
    object Loading : ScreenStatus()
    object Success : ScreenStatus()
    data class Error(val errorType: ErrorTypeUI) : ScreenStatus()
}
