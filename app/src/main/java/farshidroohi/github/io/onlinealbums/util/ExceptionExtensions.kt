package farshidroohi.github.io.onlinealbums.util

import androidx.annotation.StringRes
import farshidroohi.github.io.onlinealbums.R
import farshidroohi.github.io.onlinealbums.data.model.ErrorEntity

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */

@StringRes
fun ErrorEntity.toStringResource(): Int {

    return when (this) {
        is ErrorEntity.Network -> R.string.error_network
        is ErrorEntity.AccessDenied -> R.string.error_access_denied
        is ErrorEntity.NotFound -> R.string.error_not_found
        is ErrorEntity.ServiceUnavailable -> R.string.error_service_unavailable
        is ErrorEntity.Unknown -> R.string.error_unknown
        is ErrorEntity.CustomErrorFromServer -> 0
    }
}