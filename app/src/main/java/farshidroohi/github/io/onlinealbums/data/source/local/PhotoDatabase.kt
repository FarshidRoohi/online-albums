package farshidroohi.github.io.onlinealbums.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import farshidroohi.github.io.onlinealbums.model.Photo

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/15/22.
 */
@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photosDao(): PhotosDao
}