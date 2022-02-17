package farshidroohi.github.io.onlinealbums.data.source

import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
interface PhotosRepository {
    suspend fun getPhotos(isForceUpdate: Boolean): Result<List<Photo>>
    suspend fun getPhoto(id: String): Result<Photo>
    suspend fun deletePhotos()
    suspend fun savePhotos(photos: List<Photo>)
}