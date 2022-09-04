package karpiuk.ivan.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun artistNameTextStyle() = TextStyle(
    color = colorResource(id = R.color.cell_item_subtitle_color),
    fontSize = fontSizeResource(id = R.integer.cell_sub_title_font_size),
    fontWeight = FontWeight.Medium
)

@Composable
fun itemNameTextStyle() = TextStyle(
    color = Color.White,
    fontSize = fontSizeResource(id = R.integer.cell_title_font_size),
    fontWeight = FontWeight.SemiBold
)