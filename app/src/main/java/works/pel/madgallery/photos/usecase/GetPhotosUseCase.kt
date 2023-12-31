package works.pel.madgallery.photos.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import works.pel.madgallery.photos.repository.RemotePhotosRepository
import works.pel.madgallery.photos.repository.LocalPhotosRepository
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val remoteRepo: RemotePhotosRepository,
    private val localRepo: LocalPhotosRepository
) {

    suspend fun getPhotos(): List<Photo> {
        return withContext(dispatcher) {
            val photos = remoteRepo.getPhotos()
            localRepo.savePhotos(photos)
            localRepo.getPhotos()
        }
    }
}
