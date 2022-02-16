package farshidroohi.github.io.onlinealbums.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import farshidroohi.github.io.onlinealbums.data.PhotosRepository
import farshidroohi.github.io.onlinealbums.data.source.PhotosRepositoryImpl
import farshidroohi.github.io.onlinealbums.data.source.local.PhotoDatabase
import farshidroohi.github.io.onlinealbums.data.source.local.PhotosDao
import farshidroohi.github.io.onlinealbums.data.source.local.PhotosLocalSource
import farshidroohi.github.io.onlinealbums.data.source.network.ApiService
import farshidroohi.github.io.onlinealbums.data.source.network.PhotoNetworkSource
import javax.inject.Singleton

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModules {

    @Provides
    @Singleton
    fun providePhotosRepository(
        photosLocalSource: PhotosLocalSource,
        photoNetworkSource: PhotoNetworkSource
    ): PhotosRepository {
        return PhotosRepositoryImpl(photosLocalSource, photoNetworkSource)
    }
    @Provides
    fun providePhotoNetworkSource(apiService: ApiService): PhotoNetworkSource {
        return PhotoNetworkSource(apiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    fun providePhotosLocalSource(photosDao: PhotosDao): PhotosLocalSource {
        return PhotosLocalSource(photosDao)
    }

    @Provides
    fun providePhotosDao(photosDatabase: PhotoDatabase): PhotosDao {
        return photosDatabase.photosDao()
    }

    @Provides
    @Singleton
    fun providePhotosDatabase(@ApplicationContext appContext: Context): PhotoDatabase {
        return Room.databaseBuilder(
            appContext,
            PhotoDatabase::class.java,
            "db_photos"
        ).build()
    }
}