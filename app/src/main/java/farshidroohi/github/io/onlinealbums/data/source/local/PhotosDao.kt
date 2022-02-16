package farshidroohi.github.io.onlinealbums.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import farshidroohi.github.io.onlinealbums.data.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos")
    fun getPhotos(): List<Photo>

    @Query("DELETE FROM Photos")
    suspend fun deletePhotos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photo: Photo)
}