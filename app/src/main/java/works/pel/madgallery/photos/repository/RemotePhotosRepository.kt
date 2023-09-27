package works.pel.madgallery.photos.repository

import works.pel.madgallery.photos.api.PhotosApi
import works.pel.madgallery.photos.mapper.PhotosMapper
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

class RemotePhotosRepository @Inject constructor(
    private val mapper: PhotosMapper,
    private val photosApi: PhotosApi
) {

    suspend fun getPhotos(): List<Photo> {
        return mapper.mapFromApi(photosApi.getPhotos())
    }
}
