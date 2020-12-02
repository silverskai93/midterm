package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeApiResponse(
    val Title: String?,
    val Poster: String?
): Parcelable