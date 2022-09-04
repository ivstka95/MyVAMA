package karpiuk.ivan.myvama.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun rememberFeedAppState(navHostController: NavHostController) = remember(navHostController) {
    FeedAppState(navHostController)
}

@Stable
class FeedAppState(val navHostController: NavHostController) {

    fun navigate(destination: String) {
        navHostController.navigate(destination)
    }

    fun onBackClick() {
        navHostController.popBackStack()
    }
}