package karpiuk.ivan.feature_feed

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import karpiuk.ivan.model.Feed
import karpiuk.ivan.model.Result
import karpiuk.ivan.ui.*
import karpiuk.ivan.ui.R

@Composable
fun FeedRoute(
    onItemClick: (Result, String) -> Unit,
    paddings: PaddingValues,
    modifier: Modifier = Modifier,
    feedViewModel: FeedViewModel = hiltViewModel()
) {
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()
    SideEffect {
        val window = (view.context as Activity).window
        val windowInsetsControllerCompat = WindowCompat.getInsetsController(window, view)
        windowInsetsControllerCompat.isAppearanceLightStatusBars = !darkTheme && true
    }

    val state by feedViewModel.uiState.collectAsState()
    FeedContent(
        state = state,
        onItemClick = onItemClick,
        paddings = paddings,
        modifier = modifier
    )
}

@Composable
fun FeedContent(
    state: FeedUiState,
    onItemClick: (Result, String) -> Unit,
    paddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    when (state) {
        FeedUiState.Loading -> LoadingIndicator(modifier = modifier)
        is FeedUiState.Error -> Error(message = state.message, retryAction = state.retryAction, modifier = modifier)
        is FeedUiState.Ready -> ContentGrid(
            items = state.feed.results,
            copyright = state.feed.copyright,
            onItemClick = onItemClick,
            paddings = paddings,
            modifier = modifier
        )
    }
}

@Composable
fun ContentGrid(
    items: List<Result>,
    copyright: String,
    onItemClick: (Result, String) -> Unit,
    paddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box() {
        val listState = rememberLazyGridState()
        val toolbarState by remember {
            derivedStateOf {
                if (listState.firstVisibleItemIndex > 0)
                    ExpandableToolbarState.COLLAPSED
                else
                    ExpandableToolbarState.EXPANDED
            }
        }

        val defaultPadding = dimensionResource(id = R.dimen.default_content_padding)
        val expandedToolbarHeight = dimensionResource(id = karpiuk.ivan.feature_feed.R.dimen.expanded_toolbar_height)
        val collapsedToolbarHeight = dimensionResource(id = karpiuk.ivan.feature_feed.R.dimen.collapsed_toolbar_height)

        LazyVerticalGrid(
            state = listState,
            contentPadding = PaddingValues(
                start = defaultPadding,
                top = defaultPadding + expandedToolbarHeight + paddings.calculateTopPadding(),
                end = defaultPadding,
                bottom = defaultPadding + paddings.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_items_spacing)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.default_items_spacing)),
            columns = GridCells.Fixed(2),
            modifier = modifier.animateContentSize()
        ) {
            items(items, key = { it.id }) {
                ContentItem(
                    data = it,
                    onItemClick = { item -> onItemClick(item, copyright) }
                )
            }
        }

        ExpandableToolbar(
            text = stringResource(id = karpiuk.ivan.feature_feed.R.string.feed_title),
            expandedHeight = expandedToolbarHeight,
            collapsedHeight = collapsedToolbarHeight,
            state = toolbarState,
            topPadding = paddings.calculateTopPadding()
        )
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
            AutosizedImage(imageUrl = data.artworkUrl100, modifier = Modifier.fillMaxSize())
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
    Column(
        modifier = modifier
            .padding(all = dimensionResource(id = R.dimen.default_content_padding))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message ?: stringResource(id = R.string.default_error_message))
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_items_spacing)))
        VamaStyledButton(text = stringResource(id = R.string.retry), onClick = retryAction)
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ExpandableToolbar(
    text: String,
    state: ExpandableToolbarState,
    expandedHeight: Dp,
    collapsedHeight: Dp,
    topPadding: Dp,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.97f))
                .animateContentSize()
                .height(
                    topPadding + (if (state == ExpandableToolbarState.EXPANDED)
                        expandedHeight
                    else
                        collapsedHeight)
                )
        )

        AnimatedVisibility(
            visible = state == ExpandableToolbarState.EXPANDED,
            modifier = Modifier
                .fillMaxWidth()
                .height(expandedHeight + topPadding),
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut(animationSpec = tween(durationMillis = 200))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = text, style = TextStyle(
                        fontSize = fontSizeResource(id = R.integer.expanded_toolbar_title_font_size),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        letterSpacing = EXPANDED_TITLE_LETTER_SPACING,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            start = dimensionResource(id = R.dimen.default_content_padding),
                            top = topPadding + dimensionResource(id = R.dimen.default_items_spacing)
                        )
                )
            }
        }
        AnimatedVisibility(
            visible = state == ExpandableToolbarState.COLLAPSED,
            modifier = Modifier
                .fillMaxWidth()
                .height(collapsedHeight + topPadding),
            enter = fadeIn(),
            exit = fadeOut(animationSpec = tween(durationMillis = 200))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = text,
                    style = TextStyle(
                        fontSize = fontSizeResource(id = R.integer.collapsed_toolbar_title_font_size),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        letterSpacing = COLLAPSED_TITLE_LETTER_SPACING,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = topPadding + dimensionResource(id = R.dimen.default_items_spacing))
                )
            }
        }
    }
}

enum class ExpandableToolbarState {
    EXPANDED,
    COLLAPSED
}

val EXPANDED_TITLE_LETTER_SPACING = (-1.36).sp
val COLLAPSED_TITLE_LETTER_SPACING = (-0.64).sp

sealed interface FeedUiState {
    object Loading : FeedUiState
    data class Error(val message: String?, val retryAction: () -> Unit) : FeedUiState
    data class Ready(val feed: Feed) : FeedUiState
}