package karpiuk.ivan.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karpiuk.ivan.domain.*
import karpiuk.ivan.domain.useCases.GetFeedStreamUseCase
import karpiuk.ivan.domain.useCases.UpdateFeedUseCase
import karpiuk.ivan.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    getFeedStreamUseCase: GetFeedStreamUseCase,
    updateFeedUseCase: UpdateFeedUseCase
) : ViewModel() {

    private val feedParams = FeedParams(
        MediaType.MUSIC,
        Storefront.UNITED_STATES,
        Type.ALBUMS,
        Feed.MOST_PLAYED,
        100,
        Format.JSON
    )

    private val feedStream = getFeedStreamUseCase(feedParams)
    private val updateFeedStatusStream = updateFeedUseCase(feedParams)

    val uiState: StateFlow<FeedUiState> = combine(feedStream, updateFeedStatusStream) { feed, updateStatus ->
        when {
            feed != null -> FeedUiState.Ready(feed)
            updateStatus is Result.Error -> FeedUiState.Error(updateStatus.exception?.message) {
                updateFeedStatusStream.launchIn(
                    viewModelScope
                )
            }
            else -> FeedUiState.Loading
        }
    }
        .distinctUntilChanged()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FeedUiState.Loading
        )
}