package karpiuk.ivan.domain

import kotlinx.coroutines.flow.Flow
import karpiuk.ivan.common.Result
import karpiuk.ivan.model.Feed

interface FeedRepository {
    fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Flow<Result<Feed>>
}