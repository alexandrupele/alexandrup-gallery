package works.pel.madgallery.photos.viewmodel

import android.util.Log
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

@HiltViewModel
class PhotosViewModel @Inject constructor(
    val useCase: GetPhotosUseCase
) : ViewModel() {

    private val _listOfPhotos: MutableState<List<Photo>> = mutableStateOf(emptyList())
    val listOfPhotos: State<List<Photo>> = _listOfPhotos

    init {
        viewModelScope.launch {
            val photos = useCase.getPhotos()
            _listOfPhotos.value = photos
        }
    }
}
