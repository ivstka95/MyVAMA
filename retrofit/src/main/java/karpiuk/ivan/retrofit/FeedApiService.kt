package karpiuk.ivan.retrofit

import karpiuk.ivan.retrofit.model.RestFeedModel
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedApiService {
    @GET("api/v2/{storefront}/{mediaType}/{feed}/{resultLimit}/{type}.{format}")
    suspend fun getFeed(
        @Path("storefront") storefront: String,
        @Path("mediaType") mediaType: String,
        @Path("feed") feed: String,
        @Path("resultLimit") resultLimit: Int,
        @Path("type") type: String,
        @Path("format") format: String
    ): RestFeedModel
}