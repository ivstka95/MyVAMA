package karpiuk.ivan.domain

import kotlinx.coroutines.flow.Flow
import karpiuk.ivan.model.Feed

interface FeedRepository {
    fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Flow<Feed>

    suspend fun saveFeedToCache(feed: Feed)

    suspend fun loadFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Feed
}