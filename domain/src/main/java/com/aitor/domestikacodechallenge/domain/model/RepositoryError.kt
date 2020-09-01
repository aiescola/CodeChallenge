package com.aitor.domestikacodechallenge.domain.model

sealed class RepositoryError {
    data class NetworkError(val apiError: ApiError): RepositoryError()
}