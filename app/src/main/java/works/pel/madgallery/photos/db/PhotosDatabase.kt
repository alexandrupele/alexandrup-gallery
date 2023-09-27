package works.pel.madgallery.photos.db

import androidx.room.Database
import androidx.room.RoomDatabase
import works.pel.madgallery.photos.db.PhotosDatabase.Companion.DATABASE_VERSION
import works.pel.madgallery.photos.db.model.Photo

@Database(
    entities = [Photo::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class PhotosDatabase : RoomDatabase() {

    abstract fun provideDao(): PhotosDao

    companion object {
        const val DATABASE_NAME = "photos-database"
        const val DATABASE_VERSION = 1
    }
}
