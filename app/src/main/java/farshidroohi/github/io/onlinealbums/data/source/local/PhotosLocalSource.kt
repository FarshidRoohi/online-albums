package farshidroohi.github.io.onlinealbums.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */

class PhotosLocalSource @Inject constructor(
    private val photosDao: PhotosDao
) : PhotosRepository {

    override suspend fun getPhotos(isForceUpdate: Boolean): LiveData<Result<List<Photo>>> {
        return photosDao.getPhotos().map {
            Result.Success(it)
        }
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