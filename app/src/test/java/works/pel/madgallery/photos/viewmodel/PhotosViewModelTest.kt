package works.pel.madgallery.photos.viewmodel

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.photos.usecase.GetPhotosUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    private val useCase: GetPhotosUseCase = mockk()

    private lateinit var viewModel: PhotosViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = PhotosViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun contentIsLoading() {
        assertEquals(
            ViewState.Loading,
            viewModel.viewState.value
        )
    }

    @Test
    fun contentIsPhotos() {
        val photos = listOf(
            Photo(
                albumId = 1,
                id = 2,
                thumbnailUrl = "thumbnailUrl",
                title = "title",
                url = "url"
            )
        )
        coEvery { useCase.getPhotos() } returns photos
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            ViewState.Success(photos),
            viewModel.viewState.value
        )
    }

    @Test
    fun contentIsError() {
        val errorMessage = "error"
        coEvery { useCase.getPhotos() } throws RuntimeException(errorMessage)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            ViewState.Error(errorMessage),
            viewModel.viewState.value
        )
    }
}
