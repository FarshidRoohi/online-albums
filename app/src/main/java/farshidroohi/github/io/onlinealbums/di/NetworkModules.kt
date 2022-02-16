package farshidroohi.github.io.onlinealbums.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import farshidroohi.github.io.onlinealbums.BuildConfig
import farshidroohi.github.io.onlinealbums.data.source.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Farshid Roohi.
 * OnlineAlbums | Copyrights 2/16/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: MoshiConverterFactory): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(moshi)
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(loggingInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {

        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return HttpLoggingInterceptor()
            .setLevel(level)
    }

    @Provides
    @Singleton
    fun provideMoshiConverter(): MoshiConverterFactory {
        return MoshiConverterFactory
            .create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
    }
}