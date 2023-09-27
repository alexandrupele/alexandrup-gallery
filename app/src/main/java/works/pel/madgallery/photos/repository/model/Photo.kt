package works.pel.madgallery.photos.repository.model

data class Photo(
    val albumId: Long,
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
