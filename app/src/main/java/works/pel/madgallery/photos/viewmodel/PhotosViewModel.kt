package works.pel.madgallery.photos.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.photos.usecase.GetPhotosUseCase
import javax.inject.Inject

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val photos: List<Photo>) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
}

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val useCase: GetPhotosUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState> = mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState> = _viewState

    init {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val photos = useCase.getPhotos()
                _viewState.value = ViewState.Success(photos)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
