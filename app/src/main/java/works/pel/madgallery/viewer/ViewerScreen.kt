package works.pel.madgallery.viewer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ViewerScreen(
    photoId: Int?
) {
    Text(photoId.toString())
}
