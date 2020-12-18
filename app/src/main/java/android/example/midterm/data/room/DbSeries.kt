package android.example.midterm.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class DbSeries(
    @PrimaryKey(autoGenerate = false)
    val seriesName: String

)