package farshidroohi.github.io.onlinealbums.util

import farshidroohi.github.io.onlinealbums.data.model.ErrorEntity
import retrofit2.Response

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */

fun <T> Response<T>.getErrorObject(): ErrorEntity {
    return when (this.code()) {
        404 -> ErrorEntity.NotFound
        403 -> ErrorEntity.AccessDenied
        in 500..599 -> ErrorEntity.ServiceUnavailable
        else -> {
            ErrorEntity.Unknown
        }
    }
}

fun <T> Response<T>.isSuccessfully(): Boolean {
    return this.isSuccessful && this.body() != null
}
