package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OverViewData(
    val Title: String?,
    val Year: String?,
    val Rated: String?,
    val Released: String?,
    val Season: Int?,
    val Episode: Int?,
    val Runtime: String?,
    val imbdID: String?,
    val seriesID: String?

): Parcelable