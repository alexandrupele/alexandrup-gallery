package works.pel.madgallery.photos.mapper

import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject
import works.pel.madgallery.photos.api.model.Photo as ApiPhoto
import works.pel.madgallery.photos.db.model.Photo as DatabasePhoto

class PhotosMapper @Inject constructor() {

    fun mapFromApi(photos: List<ApiPhoto>) = photos.map { apiPhoto ->
        Photo(
            albumId = apiPhoto.albumId,
            id = apiPhoto.id,
            thumbnailUrl = apiPhoto.thumbnailUrl,
            title = apiPhoto.title,
            url = apiPhoto.url
        )
    }

    fun mapFromDatabase(photo: DatabasePhoto) = Photo(
        albumId = photo.albumId,
        id = photo.id,
        thumbnailUrl = photo.thumbnailUrl,
        title = photo.title,
        url = photo.url
    )

    fun mapFromDatabase(photos: List<DatabasePhoto>) = photos.map { databasePhoto ->
        mapFromDatabase(databasePhoto)
    }

    fun mapToDatabaseModel(photos: List<Photo>) = photos.map { photo ->
        DatabasePhoto(
            albumId = photo.albumId,
            id = photo.id,
            thumbnailUrl = photo.thumbnailUrl,
            title = photo.title,
            url = photo.url
        )
    }
}
