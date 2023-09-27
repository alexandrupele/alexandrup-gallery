package works.pel.madgallery.photos.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import works.pel.madgallery.photos.repository.LocalPhotosRepository
import works.pel.madgallery.photos.repository.RemotePhotosRepository
import works.pel.madgallery.photos.repository.model.Photo

class GetPhotosUseCaseTest {

    private val dispatcher = StandardTestDispatcher()

    private val remoteRepo: RemotePhotosRepository = mockk()
    private val localRepo: LocalPhotosRepository = mockk()

    private val useCase = GetPhotosUseCase(
        dispatcher,
        remoteRepo,
        localRepo
    )

    @Test
    fun savesPhotosFromRemote() = runTest(dispatcher) {
        val photos = listOf(mockk<Photo>())

        coEvery { remoteRepo.getPhotos() } returns photos
        coEvery { localRepo.savePhotos(photos) } just runs
        coEvery { localRepo.getPhotos() } returns photos

        useCase.getPhotos()
        coVerify { localRepo.savePhotos(photos) }
    }

    @Test
    fun returnsPhotosFromLocalStorage() = runTest(dispatcher) {
        val mockPhotos = listOf(mockk<Photo>())

        coEvery { remoteRepo.getPhotos() } returns emptyList()
        coEvery { localRepo.savePhotos(any()) } just runs
        coEvery { localRepo.getPhotos() } returns mockPhotos

        val photos = useCase.getPhotos()
        assertEquals(mockPhotos, photos)
    }
}
