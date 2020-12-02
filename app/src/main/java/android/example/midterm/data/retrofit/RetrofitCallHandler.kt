package android.example.midterm.data.retrofit

import android.example.midterm.data.model.networkmodel.ServiceResult
import android.util.Log
import retrofit2.Response
import java.io.IOException

object RetrofitCallHandler {
    suspend fun <T> processCall(
        call: suspend () -> Response<T>
    ): ServiceResult<T> {

        return try {
            val serviceCall = call()
            val body = serviceCall.body()
            if (serviceCall.isSuccessful && body != null) {
                ServiceResult.Success(body)
            } else {
                Log.e(
                    "RetrofitCallHandler", "Error in retrofit layer: ${
                        serviceCall.code().toString()
                    } ${serviceCall.message().orEmpty()}"
                )

                return ServiceResult.Error(
                    Exception(
                        "${serviceCall.code()}: call had exception: ${
                            serviceCall.errorBody().toString()
                        }"
                    )
                )
            }

        } catch (exception: Exception) {
            when (exception) {
                is IOException -> {
                    logExceptionMessage(
                        "No Internet detected",
                        "NETWORK IS NOT CONNECTED!"
                    )
                    ServiceResult.Error(exception)
                }

                else -> {
                    logExceptionMessage(
                        "1337",
                        exception.localizedMessage ?: ""
                    )
                    ServiceResult.Error(exception)
                }
            }
        }
    }

    private fun logExceptionMessage(code: String?, message: String) {
        val codedMessage = if (code == null) "" else "with code: $code"
        //TODO JOSE We need to implement timber logger here
    }
}