package farshidroohi.github.io.onlinealbums.data.source

import androidx.lifecycle.LiveData
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.source.local.PhotosLocalSource
import farshidroohi.github.io.onlinealbums.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotosRepositoryImpl(
    val photosLocalSource: PhotosLocalSource
) : PhotosRepository {

    override suspend fun getPhotos(isForceUpdate: Boolean): LiveData<Result<List<Photo>>> {
        return photosLocalSource.getPhotos(isForceUpdate)
    }

    override suspend fun deletePhotos() {
    }

    override suspend fun savePhotos(photos: List<Photo>) {

    }
}