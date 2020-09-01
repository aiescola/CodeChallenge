package com.aitor.domestikacodechallenge.data.network

import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.model.ApiError
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


fun createRetrofit(url: String, isDebug: Boolean): Retrofit {
    val okHttpClientBuilder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(DefaultHeadersInterceptor())

    if (isDebug) {
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
    }

    val okHttpClient = okHttpClientBuilder
        .build()
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

inline fun <reified T> createApi(retrofit: Retrofit): T = retrofit.create(T::class.java)

fun <T> Response<T>.asResource(): Resource<ApiError, T> {
    return when {
        code() == 404 -> Resource.Failure(ApiError.NotFoundError)
        code() > 400 -> Resource.Failure(ApiError.UnknownError(code()))
        body() == null -> Resource.Failure(ApiError.UnknownError(code()))
        code() == 200 -> Resource.Success(body()!!)
        else -> Resource.Failure(ApiError.UnknownError(code()))
    }
}
