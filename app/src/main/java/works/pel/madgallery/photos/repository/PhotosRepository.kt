package works.pel.madgallery.photos.repository

import works.pel.madgallery.photos.api.PhotosApi
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

interface IPhotosRepository {
    suspend fun getPhotos(): List<Photo>
}

class PhotosRepository @Inject constructor(
    val photosApi: PhotosApi
) : IPhotosRepository {
    override suspend fun getPhotos(): List<Photo> {
        return photosApi.getPhotos().map {
            Photo(
                albumId = it.albumId,
                id = it.id,
                thumbnailUrl = it.thumbnailUrl,
                title = it.title,
                url = it.url
            )
        }
    }
}
