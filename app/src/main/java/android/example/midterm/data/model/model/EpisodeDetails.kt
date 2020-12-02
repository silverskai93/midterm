package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeDetails (
    val Title: String?,
    val Released: String?,
    val Episode: String?,
    val imdbRating: String?,
    val imdbID: String?

) : Parcelable