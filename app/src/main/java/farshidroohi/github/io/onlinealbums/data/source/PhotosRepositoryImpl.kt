package farshidroohi.github.io.onlinealbums.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import farshidroohi.github.io.onlinealbums.BuildConfig
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.Photo
import farshidroohi.github.io.onlinealbums.data.source.local.PhotosLocalSource
import farshidroohi.github.io.onlinealbums.data.source.network.PhotoNetworkSource
import javax.inject.Inject

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotosRepositoryImpl @Inject constructor(
    private val photosLocalSource: PhotosLocalSource,
    private val photosNetworkSource: PhotoNetworkSource
) : PhotosRepository {

    override suspend fun getPhotos(isForceUpdate: Boolean): LiveData<Result<List<Photo>>> {
        if (isForceUpdate) {
            val response = photosNetworkSource.getPhotos(BuildConfig.SHARE_CODE)
            return if (response.isSuccessful && response.body() != null) {
                val items = response.body()!!
                photosLocalSource.deletePhotos()
                photosLocalSource.savePhotos(items)
                MutableLiveData(Result.Success(items))
            } else {
                MutableLiveData(Result.Error(Exception(response.message())))
            }
        }
        return photosLocalSource.getPhotos(isForceUpdate)
    }

    override suspend fun deletePhotos() {
    }

    override suspend fun savePhotos(photos: List<Photo>) {

    }
}