package works.pel.madgallery.viewer.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import works.pel.madgallery.photos.repository.LocalPhotosRepository
import works.pel.madgallery.photos.repository.model.Photo
import javax.inject.Inject

class LoadPhotoUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val localRepo: LocalPhotosRepository
) {

    suspend fun loadPhoto(id: Long): Photo {
        return withContext(dispatcher) {
            localRepo.getPhotoById(id)
        }
    }
}
