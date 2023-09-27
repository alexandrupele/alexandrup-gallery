package works.pel.madgallery.viewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import works.pel.madgallery.R
import works.pel.madgallery.app.ui.composable.CenterScreenLoading
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.viewer.viewmodel.ViewState
import works.pel.madgallery.viewer.viewmodel.ViewerViewModel

@Composable
fun ViewerScreen(
    viewModel: ViewerViewModel = hiltViewModel(),
    photoId: Long?
) {
    DisposableEffect(key1 = Unit) {
        photoId?.let { viewModel.loadPhoto(it) }
        onDispose { }
    }

    val viewState by remember {
        viewModel.viewState
    }

    when (val state = viewState) {
        is ViewState.Loading -> CenterScreenLoading()
        is ViewState.Success -> Photo(state.photo)
        is ViewState.Error -> Text(stringResource(R.string.could_not_load_photo))
    }
}

@Composable
fun Photo(photo: Photo) {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = rememberAsyncImagePainter(
            photo.url,
            placeholder = painterResource(id = R.drawable.placeholder)
        ),
        contentDescription = photo.title
    )
}
