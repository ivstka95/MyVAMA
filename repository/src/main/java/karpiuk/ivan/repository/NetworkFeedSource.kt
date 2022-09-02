package karpiuk.ivan.repository

import karpiuk.ivan.repository.model.network.NetworkFeed

interface NetworkFeedSource {
    suspend fun getFeed(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): NetworkFeed
}