package karpiuk.ivan.feature_details

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import karpiuk.ivan.ui.*
import karpiuk.ivan.ui.R

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
    modifier: Modifier = Modifier
) {
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
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
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
                Column() {
                    Text(
                        text = artistName,
                        style = artistNameTextStyle().copy(
                            color = colorResource(id = R.color.details_subtitle_color),
                            fontSize = fontSizeResource(id = R.integer.details_sub_title_font_size),
                            letterSpacing = detailsSubTitleLetterSpacing,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                    Text(
                        text = name,
                        style = itemNameTextStyle().copy(
                            color = Color.Black,
                            fontSize = fontSizeResource(id = R.integer.details_title_font_size),
                            fontWeight = FontWeight.Bold,
                            letterSpacing = detailsTitleLetterSpacing
                        )
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_items_spacing)))
                    Text(
                        text = genre,
                        style = artistNameTextStyle().copy(
                            color = colorResource(id = R.color.blue_color),
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier
                            .border(
                                1.dp,
                                colorResource(id = R.color.blue_color),
                                RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))
                            )
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
                        style = artistNameTextStyle().copy(fontWeight = FontWeight.Medium)
                    )
                    Text(text = copyright, style = artistNameTextStyle().copy(fontWeight = FontWeight.Medium))
                    artistLink?.let {
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.large_items_spacing)))
                        VamaStyledButton(text = stringResource(id = R.string.visit_album)) {
                            onAlbumPageClick(it)
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.padding(dimensionResource(id = R.dimen.default_content_padding))) {
            FilledIconButton(
                onClick = onBackClick,
                modifier = Modifier.size(dimensionResource(id = R.dimen.back_button_size)),
                colors = IconButtonDefaults.iconButtonColors(containerColor = colorResource(id = R.color.back_button_color))
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