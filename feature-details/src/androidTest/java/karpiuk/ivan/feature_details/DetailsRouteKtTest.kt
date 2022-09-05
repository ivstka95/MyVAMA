package karpiuk.ivan.feature_details

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import org.junit.Before
import org.junit.Rule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Test


internal class DetailsRouteKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    private lateinit var visitAlbum: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            visitAlbum = getString(karpiuk.ivan.ui.R.string.visit_album)
        }
    }

    @Test
    fun visitTheAlbumButton_whenUrlNotNull_isShown() {
        composeTestRule.setContent {
            DetailsRoute(
                onBackClick = { },
                onAlbumPageClick = {},
                artwork = "",
                name = "",
                artistName = "",
                genre = "",
                releaseDate = "2022-08-19",
                copyright = "",
                artistLink = "",
                paddings = PaddingValues()
            )
        }

        composeTestRule
            .onNodeWithText(visitAlbum)
            .assertIsDisplayed()
    }

    @Test
    fun visitTheAlbumButton_whenUrlNull_isNotShown() {
        composeTestRule.setContent {
            DetailsRoute(
                onBackClick = { },
                onAlbumPageClick = {},
                artwork = "",
                name = "",
                artistName = "",
                genre = "",
                releaseDate = "2022-08-19",
                copyright = "",
                artistLink = null,
                paddings = PaddingValues()
            )
        }

        composeTestRule
            .onNodeWithText(visitAlbum)
            .assertDoesNotExist()
    }
}