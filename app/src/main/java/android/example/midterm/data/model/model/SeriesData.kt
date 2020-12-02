package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesData(
    val Title: String?,
    val totalSeasons: Int?

): Parcelable
