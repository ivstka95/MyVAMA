package karpiuk.ivan.feature_feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import karpiuk.ivan.model.Feed
import karpiuk.ivan.model.Result
import karpiuk.ivan.ui.R
import karpiuk.ivan.ui.artistNameTextStyle
import karpiuk.ivan.ui.itemNameTextStyle

@Composable
fun FeedRoute(
    onItemClick: (Result, String) -> Unit,
    modifier: Modifier = Modifier,
    feedViewModel: FeedViewModel = hiltViewModel()
) {
    val state by feedViewModel.uiState.collectAsState()
    FeedContent(
        state = state,
        onItemClick = onItemClick,
        modifier = modifier
    )
}

@Composable
fun FeedContent(
    state: FeedUiState,
    onItemClick: (Result, String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        FeedUiState.Loading -> LoadingIndicator(modifier = modifier)
        is FeedUiState.Error -> Error(message = state.message, retryAction = state.retryAction, modifier = modifier)
        is FeedUiState.Ready -> ContentGrid(
            items = state.feed.results,
            copyright = state.feed.copyright,
            onItemClick = onItemClick,
            modifier = modifier
        )
    }
}

@Composable
fun ContentGrid(
    items: List<Result>,
    copyright: String,
    onItemClick: (Result, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.default_content_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_items_spacing)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_items_spacing)),
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(items, key = { it.id }) {
            ContentItem(data = it, onItemClick = { item -> onItemClick(item, copyright) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentItem(data: Result, onItemClick: (Result) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))),
        onClick = { onItemClick(data) }
    ) {
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.artworkUrl100)
                    .crossfade(true)
                    .build(),
                contentDescription = data.artworkUrl100,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                colorResource(id = R.color.gradient_color)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.cell_content_padding))
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = data.name,
                    style = itemNameTextStyle(),
                    maxLines = integerResource(id = R.integer.cell_title_max_lines),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = data.artistName,
                    style = artistNameTextStyle(),
                    maxLines = integerResource(id = R.integer.cell_sub_title_max_lines),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Composable
fun Error(message: String?, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Text(text = message ?: stringResource(id = R.string.default_error_message))
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Text(text = "Loading, please wait...", modifier = modifier)
}

sealed interface FeedUiState {
    object Loading : FeedUiState
    data class Error(val message: String?, val retryAction: () -> Unit) : FeedUiState
    data class Ready(val feed: Feed) : FeedUiState
}