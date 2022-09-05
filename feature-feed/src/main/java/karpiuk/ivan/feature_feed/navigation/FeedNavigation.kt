package karpiuk.ivan.feature_feed.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import karpiuk.ivan.feature_feed.FeedRoute
import karpiuk.ivan.model.Result
import karpiuk.ivan.navigation.AppNavigationDestination

object FeedDestination : AppNavigationDestination {
    override val route = "feed_route"
}

fun NavGraphBuilder.feedGraph(
    paddings: PaddingValues,
    onItemClick: (Result, String) -> Unit
) {
    composable(
        route = FeedDestination.route,
    ) {
        FeedRoute(
            paddings = paddings,
            onItemClick = onItemClick
        )
    }
}