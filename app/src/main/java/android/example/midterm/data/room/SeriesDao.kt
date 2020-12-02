package android.example.midterm.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SeriesDao {
    @Query("SELECT * FROM DbSeries")
    fun getAll(): List<DbSeries>

    @Insert
    fun insertAll(vararg series: DbSeries)

    @Delete
    fun delete(series: DbSeries)

}