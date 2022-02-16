package farshidroohi.github.io.onlinealbums.data.source

import farshidroohi.github.io.onlinealbums.BuildConfig
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.Result
import farshidroohi.github.io.onlinealbums.data.model.ErrorEntity
import farshidroohi.github.io.onlinealbums.data.model.Photo
import farshidroohi.github.io.onlinealbums.data.source.local.PhotosLocalSource
import farshidroohi.github.io.onlinealbums.data.source.network.PhotoNetworkSource
import java.io.IOException
import javax.inject.Inject
import farshidroohi.github.io.onlinealbums.util.getErrorObject
import farshidroohi.github.io.onlinealbums.util.isSuccessfully


/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotosRepositoryImpl @Inject constructor(
    private val photosLocalSource: PhotosLocalSource,
    private val photosNetworkSource: PhotoNetworkSource
) : PhotosRepository {

    override suspend fun getPhotos(isForceUpdate: Boolean): Result<List<Photo>> {

        val localItems = photosLocalSource.getPhotos()

        if (!isForceUpdate && localItems.isNotEmpty()) {
            return Result.Success(localItems)
        }

        return try {
            val response = photosNetworkSource.getPhotos(BuildConfig.SHARE_CODE)
            if (response.isSuccessfully()) {
                val items = response.body()!!
                deletePhotos()
                savePhotos(items)
                Result.Success(items)
            } else {
                val errorEntity = response.getErrorObject()

                // or we can parse custom error from server and use ErrorEntity.CustomErrorMessage(myCustomErrorObject)
//                val customError = ErrorEntity.CustomErrorFromServer(myCustomErrorObject)
                Result.Error(errorEntity)
            }
        } catch (exception: IOException) {
            Result.Error(ErrorEntity.Network)
        }
    }

    override suspend fun deletePhotos() {
        photosLocalSource.deletePhotos()
    }

    override suspend fun savePhotos(photos: List<Photo>) {
        photosLocalSource.savePhotos(photos)
    }
}