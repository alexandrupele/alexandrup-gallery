package works.pel.madgallery.viewer.viewmodel

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
import works.pel.madgallery.viewer.usecase.LoadPhotoUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class ViewerViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    private val useCase: LoadPhotoUseCase = mockk()

    private lateinit var viewModel: ViewerViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = ViewerViewModel(useCase)
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
    fun contentIsPhoto() {
        val photo: Photo = mockk()
        coEvery { useCase.loadPhoto(1) } returns photo
        viewModel.loadPhoto(1)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            ViewState.Success(photo),
            viewModel.viewState.value
        )
    }

    @Test
    fun contentIsError() {
        val errorMessage = "error"
        coEvery { useCase.loadPhoto(1) } throws RuntimeException(errorMessage)
        viewModel.loadPhoto(1)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(
            ViewState.Error(errorMessage),
            viewModel.viewState.value
        )
    }
}


