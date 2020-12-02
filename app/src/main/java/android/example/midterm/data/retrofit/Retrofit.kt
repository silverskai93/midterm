package android.example.midterm.data.retrofit
import android.example.midterm.data.api.Api
import android.example.midterm.data.api.Api2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    fun getData(): Api {
        val BASE_URL ="https://www.omdbapi.com"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Api::class.java)
    }

    fun getData2(): Api2 {
        val BASE_URL ="https://www.omdbapi.com/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Api2::class.java)
    }
}