package works.pel.madgallery.photos.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import works.pel.madgallery.photos.db.model.Photo

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<Photo>

    @Query("SELECT * FROM photos WHERE id = :photoId")
    suspend fun getPhotoById(photoId: Long): Photo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotos(photos: List<Photo>)
}
