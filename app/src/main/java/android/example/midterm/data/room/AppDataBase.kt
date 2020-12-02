package android.example.midterm.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DbSeries::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun seriesDao(): SeriesDao
}