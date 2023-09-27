package works.pel.madgallery.photos.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import works.pel.madgallery.photos.api.PhotosApi
import works.pel.madgallery.photos.api.model.PhotosResponse
import works.pel.madgallery.photos.mapper.PhotosMapper
import works.pel.madgallery.photos.repository.model.Photo

class RemotePhotosRepositoryTest {
    private val photosMapper: PhotosMapper = mockk()
    private val photosApi: PhotosApi = mockk()

    private val repo = RemotePhotosRepository(
        photosMapper,
        photosApi
    )

    @Test
    fun fetchesPhotosFromApi() = runTest {
        val response = PhotosResponse().apply { add(mockk()) }
        coEvery { photosApi.getPhotos() } returns response
        val expectedPhotos = listOf(mockk<Photo>())
        coEvery { photosMapper.mapFromApi(response) } returns expectedPhotos

        assertEquals(expectedPhotos, repo.getPhotos())
    }
}
