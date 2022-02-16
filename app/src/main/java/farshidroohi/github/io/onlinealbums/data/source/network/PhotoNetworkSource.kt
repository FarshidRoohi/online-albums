package farshidroohi.github.io.onlinealbums.data.source.network

import javax.inject.Inject

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
class PhotoNetworkSource @Inject constructor(private val apiService: ApiService) {


    suspend fun getPhotos(path: String) = apiService.getPhotos(path)
}