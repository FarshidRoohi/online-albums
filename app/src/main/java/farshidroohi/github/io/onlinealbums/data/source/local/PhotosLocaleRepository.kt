package farshidroohi.github.io.onlinealbums.data.source.local

import farshidroohi.github.io.onlinealbums.data.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
interface PhotosLocaleRepository {
    suspend fun getPhotos(): List<Photo>
    suspend fun deletePhotos()
    suspend fun savePhotos(photos: List<Photo>)
}