package android.example.midterm.data.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EpisodeData(
    val Title: String?,
    val totalSeasons: Int?,
    val Season: String?,
    val Episodes: List<EpisodeDetails?>?
) : Parcelable
