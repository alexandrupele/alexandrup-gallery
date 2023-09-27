package works.pel.madgallery.viewer.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.viewer.usecase.LoadPhotoUseCase
import javax.inject.Inject

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val photo: Photo) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
}

@HiltViewModel
class ViewerViewModel @Inject constructor(
    private val useCase: LoadPhotoUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState> = mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState> = _viewState

    fun loadPhoto(photoId: Long) {
        _viewState.value = ViewState.Loading
        viewModelScope.launch {
            try {
                val photo = useCase.loadPhoto(photoId)
                _viewState.value = ViewState.Success(photo)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
