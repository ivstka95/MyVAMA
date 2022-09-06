package karpiuk.ivan.feature_details

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import karpiuk.ivan.ui.*
import karpiuk.ivan.ui.R
import karpiuk.ivan.ui.theme.Blue

@Composable
fun DetailsRoute(
    onBackClick: () -> Unit,
    onAlbumPageClick: (String) -> Unit,
    artwork: String,
    name: String,
    artistName: String,
    genre: String,
    releaseDate: String,
    copyright: String,
    artistLink: String?,
    paddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        val windowInsetsControllerCompat = WindowCompat.getInsetsController(window, view)
        windowInsetsControllerCompat.isAppearanceLightStatusBars = false
    }

    DetailsContent(
        onBackClick = onBackClick,
        onAlbumPageClick = onAlbumPageClick,
        artwork = artwork,
        name = name,
        artistName = artistName,
        genre = genre,
        releaseDate = releaseDate,
        copyright = copyright,
        artistLink = artistLink,
        paddings = paddings,
        modifier = modifier
    )
}

@Composable
fun DetailsContent(
    onBackClick: () -> Unit,
    onAlbumPageClick: (String) -> Unit,
    artwork: String,
    name: String,
    artistName: String,
    genre: String,
    releaseDate: String,
    copyright: String,
    artistLink: String?,
    paddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(bottom = paddings.calculateBottomPadding())) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .run {
                    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE)
                        verticalScroll(rememberScrollState()) else this
                }
        ) {
            AutosizedImage(
                imageUrl = artwork,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(id = R.dimen.default_content_padding),
                        top = dimensionResource(id = R.dimen.default_items_spacing),
                        end = dimensionResource(id = R.dimen.default_content_padding),
                        bottom = dimensionResource(id = karpiuk.ivan.feature_details.R.dimen.details_bottom_padding)
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = artistName,
                        style = MaterialTheme.typography.bodySmall,
                    )
                    Text(
                        text = name,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_items_spacing)))
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Blue),
                        modifier = Modifier
                            .border(1.dp, Blue, RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)))
                            .padding(horizontal = dimensionResource(id = R.dimen.small_items_spacing))
                            .padding(bottom = 1.dp)
                    )
                }

                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.released, releaseDate.formattedDate(LocalContext.current)),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = copyright,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    artistLink?.let {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_items_spacing)))
                        VamaStyledButton(text = stringResource(id = R.string.visit_album)) {
                            onAlbumPageClick(it)
                        }
                    }
                }
            }
        }
        val defaultPadding = dimensionResource(id = R.dimen.default_content_padding)
        Box(
            modifier = Modifier.padding(
                start = defaultPadding,
                top = defaultPadding + paddings.calculateTopPadding(),
                end = defaultPadding,
                bottom = defaultPadding
            )
        ) {
            FilledIconButton(
                onClick = onBackClick,
                modifier = Modifier.size(dimensionResource(id = R.dimen.back_button_size)),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(id = R.color.back_button_color),
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_icon_chevron_left),
                    contentDescription = "Back button"
                )
            }
        }
    }
}

val detailsSubTitleLetterSpacing = (-0.72).sp
val detailsTitleLetterSpacing = (-1.36).sp