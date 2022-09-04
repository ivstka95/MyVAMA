package karpiuk.ivan.feature_details.navigation

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import karpiuk.ivan.feature_details.DetailsRoute
import karpiuk.ivan.navigation.AppNavigationDestination

object DetailsDestination : AppNavigationDestination {
    const val artworkArg = "artworkArg"
    const val nameArg = "nameArg"
    const val artistNameArg = "artistNameArg"
    const val genreArg = "genreArg"
    const val releaseDateArg = "releaseDateArg"
    const val copyrightArg = "copyrightArg"
    const val artistLinkArg = "artistLinkArg"

    private const val baseRoute = "details_route"

    override val route =
        "$baseRoute/{$artworkArg}/{$nameArg}/{$artistNameArg}/{$genreArg}/{$releaseDateArg}/{$copyrightArg}/?artistLinkArg={$artistLinkArg}"

    fun createNavigationRoute(
        artworkArg: String,
        nameArg: String,
        artistNameArg: String,
        genreArg: String,
        releaseDateArg: String,
        copyrightArg: String,
        artistLinkArg: String?
    ): String {
        return "$baseRoute/${
            Uri.encode(
                artworkArg
            )
        }/${
            Uri.encode(
                nameArg
            )
        }/${
            Uri.encode(
                artistNameArg
            )
        }/${
            Uri.encode(
                genreArg
            )
        }/${
            Uri.encode(
                releaseDateArg
            )
        }/${
            Uri.encode(
                copyrightArg
            )
        }/${
            Uri.encode(
                artistLinkArg
            )
        }"
    }

    fun decodeFromNavArgs(entry: NavBackStackEntry, key: String): String? {
        val encodedId = entry.arguments?.getString(key)
        return Uri.decode(encodedId)
    }
}

fun NavGraphBuilder.detailsGraph(
    onBackClick: () -> Unit,
    onArtistPageClick: () -> Unit
) {
    composable(
        route = DetailsDestination.route,
        arguments = listOf(
            navArgument(DetailsDestination.artworkArg) { type = NavType.StringType },
            navArgument(DetailsDestination.nameArg) { type = NavType.StringType },
            navArgument(DetailsDestination.artistNameArg) { type = NavType.StringType },
            navArgument(DetailsDestination.genreArg) { type = NavType.StringType },
            navArgument(DetailsDestination.releaseDateArg) { type = NavType.StringType },
            navArgument(DetailsDestination.copyrightArg) { type = NavType.StringType },
            navArgument(DetailsDestination.artistLinkArg) { type = NavType.StringType },
        )
    ) {
        DetailsRoute(
            onBackClick = onBackClick,
            onArtistPageClick = onArtistPageClick,
            artwork = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            name = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            artistName = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            genre = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            releaseDate = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            copyright = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg)!!,
            artistLink = DetailsDestination.decodeFromNavArgs(it, DetailsDestination.artworkArg),
        )
    }
}
