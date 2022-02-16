package farshidroohi.github.io.onlinealbums.data.model

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */
sealed class ErrorEntity(val custom: Nothing? = null) {

    object Network : ErrorEntity()

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    class CustomErrorFromServer(custom: Nothing) : ErrorEntity(custom)

    object Unknown : ErrorEntity()
}
