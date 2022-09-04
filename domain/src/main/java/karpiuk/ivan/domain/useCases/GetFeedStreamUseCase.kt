package karpiuk.ivan.domain.useCases

import karpiuk.ivan.domain.FeedParams
import karpiuk.ivan.domain.FeedRepository
import karpiuk.ivan.domain.UseCaseWithParams
import karpiuk.ivan.model.Feed
import javax.inject.Inject

class GetFeedStreamUseCase @Inject constructor(private val feedRepository: FeedRepository) :
    UseCaseWithParams<FeedParams, Feed?>() {

    override fun invoke(params: FeedParams) =
        feedRepository.getFeedStream(
            mediaType = params.mediaType,
            storefront = params.storefront,
            type = params.type,
            feed = params.feed,
            resultLimit = params.resultLimit,
            format = params.format
        )
}