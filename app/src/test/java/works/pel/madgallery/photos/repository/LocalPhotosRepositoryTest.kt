package works.pel.madgallery.photos.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import works.pel.madgallery.photos.db.PhotosDao
import works.pel.madgallery.photos.mapper.PhotosMapper
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.photos.db.model.Photo as DatabasePhoto

class LocalPhotosRepositoryTest {

    private val photosMapper: PhotosMapper = mockk()
    private val photosDao: PhotosDao = mockk()

    private val repo = LocalPhotosRepository(
        photosMapper,
        photosDao
    )

    @Test
    fun getsStoredPhotos() = runTest {
        val photosInDatabase = listOf(mockk<DatabasePhoto>())
        coEvery { photosDao.getPhotos() } returns photosInDatabase
        val expectedPhotos = listOf(mockk<Photo>())
        coEvery { photosMapper.mapFromDatabase(photosInDatabase) } returns expectedPhotos

        val actualPhotos = repo.getPhotos()
        assertEquals(expectedPhotos, actualPhotos)
    }

    @Test
    fun getsStoredPhotoById() = runTest {
        val photo: DatabasePhoto = mockk()
        coEvery { photosDao.getPhotoById(1) } returns photo
        val expectedPhoto: Photo = mockk()
        coEvery { photosMapper.mapFromDatabase(photo) } returns expectedPhoto

        val actualPhoto = repo.getPhotoById(1)
        assertEquals(expectedPhoto, actualPhoto)
    }

    @Test
    fun storesPhotos() = runTest {
        val photos = listOf(mockk<Photo>())
        val databasePhotos = listOf(mockk<DatabasePhoto>())
        coEvery { photosMapper.mapToDatabase(photos) } returns databasePhotos
        coEvery { photosDao.savePhotos(any()) } just runs

        repo.savePhotos(photos)
        coVerify { photosDao.savePhotos(databasePhotos) }
    }
}
