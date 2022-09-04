package karpiuk.ivan.repository

import karpiuk.ivan.domain.FeedRepository
import karpiuk.ivan.model.Feed
import karpiuk.ivan.repository.model.local.asDomainModel
import karpiuk.ivan.repository.model.local.asLocalModel
import karpiuk.ivan.repository.model.network.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultFeedRepository @Inject constructor(
    private val networkFeedSource: NetworkFeedSource,
    private val localFeedSource: LocalFeedSource
) : FeedRepository {

    override fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ) = localFeedSource.getFeedStream(mediaType, storefront, type, feed, resultLimit, format)
        .map { it?.asDomainModel() }

    override suspend fun saveFeedToCache(feed: Feed) {
        localFeedSource.saveFeedToCache(feed.asLocalModel())
    }

    override suspend fun loadFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Feed = networkFeedSource.getFeed(mediaType, storefront, type, feed, resultLimit, format).asDomainModel()
}