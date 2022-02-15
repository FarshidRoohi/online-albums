package farshidroohi.github.io.onlinealbums.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.model.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */

class PhotosLocalSource(
    private val photosDao: PhotosDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PhotosRepository {

    override suspend fun getPhotos(isForceUpdate: Boolean): LiveData<Result<List<Photo>>> {
        return photosDao.getPhotos().map { items ->
            Result.Success(items)
        }
    }

    override suspend fun deletePhotos() = withContext(ioDispatcher) {
        photosDao.deletePhotos()
    }

    override suspend fun savePhotos(photos: List<Photo>) = withContext(ioDispatcher) {
        photos.forEach { photo ->
            photosDao.insertPhoto(photo)
        }
    }
}