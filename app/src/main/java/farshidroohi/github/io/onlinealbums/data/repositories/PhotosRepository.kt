package farshidroohi.github.io.onlinealbums.data.repositories

import farshidroohi.github.io.onlinealbums.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
interface PhotosRepository {
    suspend fun getPhotos(isForceUpdate: Boolean): Result<List<Photo>>
    suspend fun deletePhotos()
}