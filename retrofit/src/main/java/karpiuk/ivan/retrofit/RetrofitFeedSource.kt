package karpiuk.ivan.retrofit

import karpiuk.ivan.repository.NetworkFeedSource
import karpiuk.ivan.repository.model.network.NetworkFeed
import karpiuk.ivan.retrofit.model.RestFeedModel
import karpiuk.ivan.retrofit.model.asExternalModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

internal interface FeedApiService {
    @GET("/{storefront}/{mediaType}/{feed}/{resultLimit}/{type}.{format}")
    suspend fun getFeed(
        storefront: String,
        mediaType: String,
        feed: String,
        resultLimit: Int,
        type: String,
        format: String
    ): RestFeedModel
}

private const val FEED_API_BASE_URL = "https://rss.applemarketingtools.com/api/v2"

internal val defaultFeedApiService = Retrofit.Builder()
    .baseUrl(FEED_API_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(FeedApiService::class.java)

internal class RetrofitFeedSource(private val feedApiService: FeedApiService) : NetworkFeedSource {

    override suspend fun getFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): NetworkFeed {
        return feedApiService.getFeed(storefront, mediaType, feed, resultLimit, type, format).feed.asExternalModel()
    }
}