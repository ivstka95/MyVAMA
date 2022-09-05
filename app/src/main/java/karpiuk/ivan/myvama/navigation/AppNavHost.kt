package karpiuk.ivan.myvama.navigation

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import karpiuk.ivan.feature_details.navigation.DetailsDestination
import karpiuk.ivan.feature_details.navigation.detailsGraph
import karpiuk.ivan.feature_feed.navigation.FeedDestination
import karpiuk.ivan.feature_feed.navigation.feedGraph
import karpiuk.ivan.myvama.R


@Composable
fun AppNavHost(
    navHostController: NavHostController,
    paddings: PaddingValues,
    onNavigateToDestination: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = FeedDestination.route
) {
    val localContext = LocalContext.current
    NavHost(navController = navHostController, startDestination = startDestination, modifier = modifier) {
        feedGraph(
            paddings = paddings,
            onItemClick = { item, copyright ->
                onNavigateToDestination(
                    DetailsDestination.createNavigationRoute(
                        item.artworkUrl100,
                        item.name,
                        item.artistName,
                        item.genres.first { !it.name.contentEquals("Music", true) }.name,
                        item.releaseDate,
                        copyright,
                        item.artistUrl
                    )
                )
            })
        detailsGraph(
            paddings = paddings,
            onBackClick = onBackClick,
            onArtistPageClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                try {
                    startActivity(localContext, browserIntent, null)
                } catch (t: Throwable) {
                    Toast.makeText(localContext.applicationContext, R.string.cant_open_link, LENGTH_SHORT).show()
                }
            })
    }
}