package works.pel.madgallery.photos.api

import retrofit2.http.GET
import works.pel.madgallery.photos.api.model.PhotosResponse

interface PhotosApi {

    @GET("photos")
    suspend fun getPhotos(): PhotosResponse
}
