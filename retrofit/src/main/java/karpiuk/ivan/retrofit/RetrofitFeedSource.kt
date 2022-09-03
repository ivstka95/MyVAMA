package karpiuk.ivan.retrofit

import karpiuk.ivan.utils.IoDispatcher
import karpiuk.ivan.repository.NetworkFeedSource
import karpiuk.ivan.repository.model.network.NetworkFeed
import karpiuk.ivan.retrofit.model.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal const val FEED_API_BASE_URL = "https://rss.applemarketingtools.com/"

class RetrofitFeedSource @Inject constructor(
    private val feedApiService: FeedApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NetworkFeedSource {

    override suspend fun getFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): NetworkFeed =
        withContext(ioDispatcher) {
            feedApiService.getFeed(storefront, mediaType, feed, resultLimit, type, format)
                .feed
                .asExternalModel()
        }
}