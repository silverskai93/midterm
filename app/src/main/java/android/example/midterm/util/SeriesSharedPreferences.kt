package android.example.midterm.util

import android.content.Context
import android.example.midterm.data.impl.SeriesSharedPreferencesImpl

interface SeriesSharedPreferences {

    suspend fun readSeries(): String?

    suspend fun writeSeries(whatever: String)

    companion object{

        fun getSharedPrefImpl(context: Context): SeriesSharedPreferences{
            return SeriesSharedPreferencesImpl(context)

        }
    }
}