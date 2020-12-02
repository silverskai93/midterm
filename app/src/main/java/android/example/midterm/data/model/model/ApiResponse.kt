package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResponse (
    val Title: String?,
    val totalSeasons: Int?,
    val imdbID: String?
): Parcelable
