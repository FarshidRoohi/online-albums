package farshidroohi.github.io.onlinealbums.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
@Entity(tableName = "photos")
@JsonClass(generateAdapter = true)
data class Photo constructor(
    @PrimaryKey
    @field:Json(name = "id")
    var id: String,
    @Json(name = "media_type")
    @ColumnInfo(name = "media_type")
    var mediaType: String,
    @ColumnInfo(name = "filename")
    var filename: String,
    @ColumnInfo(name = "size")
    var size: Int,
    @ColumnInfo(name = "created_at")
    @Json(name = "created_at")
    var createdAt: String,
    @ColumnInfo(name = "thumbnail_url")
    @Json(name = "thumbnail_url")
    var thumbnail_url: String,
    @ColumnInfo(name = "download_url")
    @Json(name = "download_url")
    var download_url: String,
    @ColumnInfo(name = "resx")
    var resx: Int,
    @ColumnInfo(name = "resy")
    var resy: Int,
) {
    fun toThumbnailUrl(): String {
        return "$thumbnail_url?w=300&h=300&m=bb"
    }
}
