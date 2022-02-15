package farshidroohi.github.io.onlinealbums.data.source.network

import farshidroohi.github.io.onlinealbums.data.model.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
interface ApiService {

    @GET("/shared/{id}/media")
    suspend fun getPhotos(@Path("id") id: String): Response<List<Photo>>
}