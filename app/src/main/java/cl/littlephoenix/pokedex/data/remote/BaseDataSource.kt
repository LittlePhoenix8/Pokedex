package cl.littlephoenix.pokedex.data.remote

import android.util.Log
import cl.littlephoenix.pokedex.helper.ResourceHelper
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResourceHelper<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return ResourceHelper.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ResourceHelper<T> {
        Log.e("ResponseError", message)
        return ResourceHelper.error("Network call has failed for a following reason: $message")
    }
}