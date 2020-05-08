package com.spinner.googlepolyproject.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
data class AssetsResults (
    //@Json(name = "assets")
    val assets: List<Asset>,
    val nextPageToken: String,
    val totalSize: Int
)

@JsonClass(generateAdapter = true)
@Parcelize
data class Asset (
    val displayName: String,
    val authorName: String,
    val description: String? = "",
    val thumbnail: File
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class File (
    val relativePath: String,
    val url: String,
    val contentType: String
) : Parcelable