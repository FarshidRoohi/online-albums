package farshidroohi.github.io.onlinealbums.model

import com.squareup.moshi.Json

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
data class Photo(
    var id: String,
    @Json(name = "media_type")
    var mediaType: String,
    var filename: String,
    var size: Int,
    @Json(name = "created_at")
    var createdAt: String,
    @Json(name = "thumbnail_url")
    var thumbnail_url: String,
    @Json(name = "download_url")
    var download_url: String,
    var resx: Int,
    var resy: Int,
)
