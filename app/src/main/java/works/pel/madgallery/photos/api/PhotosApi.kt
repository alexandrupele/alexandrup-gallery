package works.pel.madgallery.photos.api

import retrofit2.http.GET
import works.pel.madgallery.photos.api.model.PhotosResponse

interface PhotosApi {

    @GET("photos")
    suspend fun getPhotos(): PhotosResponse

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }
}
