package com.aitor.domestikacodechallenge.player.model

import android.net.Uri
import android.os.Parcelable
import androidx.core.net.toUri
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaItem(private val videoUrl: String): Parcelable {
    val uri: Uri = videoUrl.toUri()
}