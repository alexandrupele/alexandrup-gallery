package works.pel.madgallery.photos.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    val albumId: Long,
    @PrimaryKey val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
