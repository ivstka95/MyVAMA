package karpiuk.ivan.feature_feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import karpiuk.ivan.domain.*
import karpiuk.ivan.domain.useCases.GetFeedStreamUseCase
import karpiuk.ivan.domain.useCases.UpdateFeedUseCase
import karpiuk.ivan.utils.Result
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    getFeedStreamUseCase: GetFeedStreamUseCase,
    private val updateFeedUseCase: UpdateFeedUseCase
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
    private val updateFeedStatusStream = MutableSharedFlow<Result<Unit>?>(replay = 1)

    init {
        updateFeed()
    }

    private fun updateFeed() {
        viewModelScope.launch {
            updateFeedUseCase(feedParams).collect {
                updateFeedStatusStream.emit(it)
            }
        }
    }

    val uiState: StateFlow<FeedUiState> = combine(feedStream, updateFeedStatusStream) { feed, updateStatus ->
        when {
            feed != null -> FeedUiState.Ready(feed)
            updateStatus is Result.Error -> FeedUiState.Error(updateStatus.exception?.message) {
                updateFeed()
            }
            else -> FeedUiState.Loading
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FeedUiState.Loading
        )
}