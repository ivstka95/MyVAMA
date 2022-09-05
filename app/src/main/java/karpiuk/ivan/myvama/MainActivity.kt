package karpiuk.ivan.myvama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun FeedApp(
    modifier: Modifier = Modifier,
    feedAppState: FeedAppState = rememberFeedAppState(navHostController = rememberNavController())
) {
    MyVAMATheme {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            topBar = {},
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { padding ->
            Surface(
                modifier = modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppNavHost(
                    navHostController = feedAppState.navHostController,
                    paddings = padding,
                    onNavigateToDestination = { feedAppState.navigate(it) },
                    onBackClick = { feedAppState.onBackClick() },
                    modifier = modifier.fillMaxSize()
                )
            }
        }
    }
}