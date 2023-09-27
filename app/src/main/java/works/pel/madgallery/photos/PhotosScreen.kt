package works.pel.madgallery.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import works.pel.madgallery.R
import works.pel.madgallery.app.ui.composable.CenterScreenLoading
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.photos.viewmodel.PhotosViewModel
import works.pel.madgallery.photos.viewmodel.ViewState

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel = hiltViewModel(),
    onPhotoClick: (id: Long) -> Unit
) {
    val viewState by remember {
        viewModel.viewState
    }

    when (val state = viewState) {
        is ViewState.Loading -> CenterScreenLoading()
        is ViewState.Success -> PhotosList(state.photos, onPhotoClick)
        is ViewState.Error -> Text(stringResource(R.string.could_not_load_photos))
    }
}

@Composable
fun PhotosList(
    photos: List<Photo>,
    onPhotoClick: (id: Long) -> Unit
) {
    LazyColumn {
        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Photos",
                style = MaterialTheme.typography.titleLarge
            )
        }
        items(photos) { photo ->
            PhotoItem(photo) { photoId ->
                onPhotoClick(photoId)
            }
        }
    }
}

@Composable
fun PhotoItem(
    photo: Photo,
    onPhotoClick: (id: Long) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onPhotoClick(photo.id) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(0)

        ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = rememberAsyncImagePainter(
                    photo.thumbnailUrl,
                    placeholder = painterResource(id = R.drawable.placeholder)
                ),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = photo.title
            )
        }
    }
}
