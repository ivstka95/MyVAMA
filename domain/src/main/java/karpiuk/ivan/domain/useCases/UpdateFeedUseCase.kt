package karpiuk.ivan.domain.useCases

import karpiuk.ivan.domain.FeedParams
import karpiuk.ivan.domain.FeedRepository
import karpiuk.ivan.domain.UseCaseWithParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import karpiuk.ivan.utils.Result
import karpiuk.ivan.utils.asResult

class UpdateFeedUseCase @Inject constructor(private val feedRepository: FeedRepository) :
    UseCaseWithParams<FeedParams, Result<Unit>>() {
    override fun invoke(params: FeedParams): Flow<Result<Unit>> = flow {
        emit(
            feedRepository.saveFeedToCache(
                feedRepository.loadFeed(
                    mediaType = params.mediaType,
                    storefront = params.storefront,
                    type = params.type,
                    feed = params.feed,
                    resultLimit = params.resultLimit,
                    format = params.format
                )
            )
        )
    }
        .asResult()
}