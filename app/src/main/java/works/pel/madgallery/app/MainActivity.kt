package works.pel.madgallery.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import works.pel.madgallery.app.ui.theme.GalleryTheme
import works.pel.madgallery.photos.PhotosScreen
import works.pel.madgallery.viewer.ViewerScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryApp()
                }
            }
        }
    }
}

@Composable
fun GalleryApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "photos") {
        composable("photos") {
            PhotosScreen { photoId ->
                navController.navigate("viewer/${photoId}")
            }
        }

        composable(
            "viewer/{photoId}",
            arguments = listOf(navArgument("photoId") { type = NavType.LongType })
        ) {
            val photoId = remember {
                it.arguments?.getLong("photoId")
            }
            ViewerScreen(photoId = photoId)
        }
    }
}
