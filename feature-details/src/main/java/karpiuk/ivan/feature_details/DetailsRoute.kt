package karpiuk.ivan.feature_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsRoute(
    onBackClick: () -> Unit,
    onArtistPageClick: () -> Unit,
    artwork: String,
    name: String,
    artistName: String,
    genre: String,
    releaseDate: String,
    copyright: String,
    artistLink: String?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = artwork)
        Text(text = name)
        Text(text = artistName)
        Text(text = genre)
        Text(text = releaseDate)
        Text(text = copyright)
        Text(text = artistLink.toString())
    }
}