package works.pel.madgallery.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import works.pel.madgallery.photos.api.PhotosApi
import works.pel.madgallery.photos.db.PhotosDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(PhotosApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesPhotosApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        PhotosDatabase::class.java,
        PhotosDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDBDao(database: PhotosDatabase) = database.provideDao()

    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
