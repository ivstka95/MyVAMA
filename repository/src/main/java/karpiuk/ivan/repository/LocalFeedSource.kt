package karpiuk.ivan.repository

import karpiuk.ivan.repository.model.local.LocalFeed
import kotlinx.coroutines.flow.Flow

interface LocalFeedSource {
    fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Flow<LocalFeed?>

    suspend fun getFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): LocalFeed?

    suspend fun saveFeedToCache(localFeed: LocalFeed)
}