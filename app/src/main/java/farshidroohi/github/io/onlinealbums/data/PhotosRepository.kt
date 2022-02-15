package farshidroohi.github.io.onlinealbums.data

import androidx.lifecycle.LiveData
import farshidroohi.github.io.onlinealbums.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
interface PhotosRepository {
    suspend fun getPhotos(isForceUpdate: Boolean): LiveData<Result<List<Photo>>>
    suspend fun deletePhotos()
    suspend fun savePhotos(photos: List<Photo>)
}