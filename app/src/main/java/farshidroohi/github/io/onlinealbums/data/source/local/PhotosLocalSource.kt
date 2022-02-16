package farshidroohi.github.io.onlinealbums.data.source.local

import farshidroohi.github.io.onlinealbums.data.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */

class PhotosLocalSource @Inject constructor(
    private val photosDao: PhotosDao
) : PhotosLocaleRepository {

    override suspend fun getPhotos(): List<Photo> {
        return photosDao.getPhotos()
    }

    override suspend fun deletePhotos() = withContext(Dispatchers.IO) {
        photosDao.deletePhotos()
    }

    override suspend fun savePhotos(photos: List<Photo>) = withContext(Dispatchers.IO) {
        photos.forEach { photo ->
            photosDao.insertPhoto(photo)
        }
    }
}