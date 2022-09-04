package karpiuk.ivan.myvama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import karpiuk.ivan.myvama.navigation.AppNavHost
import karpiuk.ivan.myvama.ui.FeedAppState
import karpiuk.ivan.myvama.ui.rememberFeedAppState
import karpiuk.ivan.myvama.ui.theme.MyVAMATheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeedApp(
                modifier = Modifier.fillMaxSize(),
                feedAppState = rememberFeedAppState(navHostController = rememberNavController())
            )
        }
    }
}

@Composable
private fun FeedApp(
    modifier: Modifier = Modifier,
    feedAppState: FeedAppState = rememberFeedAppState(navHostController = rememberNavController())
) {
    MyVAMATheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AppNavHost(
                navHostController = feedAppState.navHostController,
                onNavigateToDestination = { feedAppState.navigate(it) },
                onBackClick = { feedAppState.onBackClick() })
        }
    }
}