package works.pel.madgallery.photos.repository

import works.pel.madgallery.photos.db.PhotosDao
import works.pel.madgallery.photos.mapper.PhotosMapper
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

class LocalPhotosRepository @Inject constructor(
    private val mapper: PhotosMapper,
    private val dao: PhotosDao
) {

    suspend fun getPhotoById(photoId: Long) : Photo {
        return mapper.mapFromDatabase(dao.getPhotoById(photoId))
    }

    suspend fun getPhotos(): List<Photo> {
        return mapper.mapFromDatabase(dao.getPhotos())
    }

    suspend fun savePhotos(photos: List<Photo>) {
        dao.savePhotos(mapper.mapToDatabase(photos))
    }
}
