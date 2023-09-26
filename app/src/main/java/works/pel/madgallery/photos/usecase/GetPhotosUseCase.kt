package works.pel.madgallery.photos.usecase

import works.pel.madgallery.photos.repository.PhotosRepository
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    val repo: PhotosRepository
) {

    suspend fun getPhotos(): List<Photo> {
        return repo.getPhotos()
    }
}
