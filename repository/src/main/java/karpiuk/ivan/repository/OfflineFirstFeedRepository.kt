package karpiuk.ivan.repository

import karpiuk.ivan.domain.FeedRepository
import karpiuk.ivan.model.Feed
import karpiuk.ivan.common.Result
import karpiuk.ivan.repository.model.network.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OfflineFirstFeedRepository(private val networkFeedSource: NetworkFeedSource) : FeedRepository {
    override fun getFeedStream(
        mediaType: String,
        storefront: String,
        type: String,
        feed: String,
        resultLimit: Int,
        format: String
    ): Flow<Result<Feed>> = flow {
        emit(Result.Loading)
        emit(
            try {
                Result.Success(
                    networkFeedSource.getFeed(
                        mediaType,
                        storefront,
                        type,
                        feed,
                        resultLimit,
                        format
                    )
                        .asDomainModel()
                )
            } catch (e: Throwable) {
                Result.Error(e)
            }
        )
    }
}