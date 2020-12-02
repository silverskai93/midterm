package android.example.midterm.data.impl

import android.content.Context
import android.example.midterm.util.SeriesSharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys


class SeriesSharedPreferencesImpl(
    context: Context
) : SeriesSharedPreferences {

    companion object {
        private const val FILE_NAME = "SeriesSharedPreferences"
        private  const val key = "key1"

    }

    private val sharedPreferences by lazy {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
        EncryptedSharedPreferences.create(
            FILE_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

    override suspend fun readSeries(): String? {
        val retrievedkey = sharedPreferences.getString(key, "undefined keyvalue pair")
        return retrievedkey
    }

    override suspend fun writeSeries(whatever: String) {

        sharedPreferences.edit { putString(key, whatever) }

    }

}