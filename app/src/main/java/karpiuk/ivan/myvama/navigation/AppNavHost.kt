package karpiuk.ivan.myvama.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import karpiuk.ivan.feature_details.navigation.DetailsDestination
import karpiuk.ivan.feature_details.navigation.detailsGraph
import karpiuk.ivan.feature_feed.navigation.FeedDestination
import karpiuk.ivan.feature_feed.navigation.feedGraph

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    onNavigateToDestination: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = FeedDestination.route
) {
    NavHost(navController = navHostController, startDestination = startDestination, modifier = modifier) {
        feedGraph(onItemClick = { item, copyright ->
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
            onBackClick = onBackClick,
            onArtistPageClick = {
                // TODO: launch view intent
            })
    }
}