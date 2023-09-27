package works.pel.madgallery.photos.api.model

data class Photo(
    val albumId: Long,
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
