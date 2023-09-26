package works.pel.madgallery.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import works.pel.madgallery.photos.repository.model.Photo
import works.pel.madgallery.photos.viewmodel.PhotosViewModel

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModel = hiltViewModel()
) {

    val listOfPhotos by remember {
        viewModel.listOfPhotos
    }

    LazyColumn {
        items(listOfPhotos) { photo ->
            PhotoItem(photo)
        }
    }

}

@Composable
fun PhotoItem(photo: Photo) {
    Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(8.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = rememberAsyncImagePainter(photo.thumbnailUrl),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = photo.title
            )
        }
    }
}
