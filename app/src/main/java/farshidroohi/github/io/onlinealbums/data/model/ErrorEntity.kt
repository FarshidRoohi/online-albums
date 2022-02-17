package farshidroohi.github.io.onlinealbums.data.model

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */
sealed class ErrorEntity(val custom: Error? = null) {

    object Network : ErrorEntity()

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    class CustomErrorFromServer(custom: Error) : ErrorEntity(custom)

    object Unknown : ErrorEntity()
}

data class Error(val code: Int, val message: String)
