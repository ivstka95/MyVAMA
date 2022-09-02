package karpiuk.ivan.domain.useCases

import karpiuk.ivan.domain.FeedRepository
import karpiuk.ivan.domain.Format
import karpiuk.ivan.domain.UseCaseWithParams
import karpiuk.ivan.common.Result
import karpiuk.ivan.model.Feed
import kotlinx.coroutines.flow.Flow

class GetFeedUseCase(private val feedRepository: FeedRepository) :
    UseCaseWithParams<GetFeedUseCase.FeedParams, Feed>() {

    override fun invoke(params: FeedParams): Flow<Result<Feed>> =
        feedRepository.getFeedStream(
            mediaType = params.mediaType,
            storefront = params.storefront,
            type = params.type,
            feed = params.feed,
            resultLimit = params.resultLimit,
            format = params.format
        )

    data class FeedParams(
        val mediaType: String,
        val storefront: String,
        val type: String,
        val feed: String,
        val resultLimit: Int = 100,
        val format: String = Format.JSON
    )
}

