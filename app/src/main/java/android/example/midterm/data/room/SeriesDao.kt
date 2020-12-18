package android.example.midterm.data.room

import androidx.room.*

@Dao
interface SeriesDao {
    @Query("SELECT * FROM DbSeries")
    fun getAll(): List<DbSeries>

    @Update
    fun updateAll(series: DbSeries)

    @Insert
    fun insertAll(vararg series: DbSeries)

    @Delete
    fun delete(series: DbSeries)

}