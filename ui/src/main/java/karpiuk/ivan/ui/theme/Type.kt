package karpiuk.ivan.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import karpiuk.ivan.ui.R

val displayBold = FontFamily(Font(R.font.sfpro_display_bold))
val textMedium = FontFamily(Font(R.font.sfpro_text_medium))
val textRegular = FontFamily(Font(R.font.sfpro_text_regular))
val textSemibold = FontFamily(Font(R.font.sfpro_text_semibold))

val VamaTypography = Typography(
    displayLarge = TextStyle(
        color = Dark,
        fontSize = 34.sp,
        fontFamily = displayBold,
        letterSpacing = (-1.36).sp
    ),
    displayMedium = TextStyle(
        color = Dark,
        fontSize = 16.sp,
        fontFamily = displayBold,
        letterSpacing = (-1.36).sp
    ),
    titleMedium = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
        fontFamily = textSemibold,
        letterSpacing = (-0.64).sp
    ),
    bodyMedium = TextStyle(
        color = Gray,
        fontSize = 12.sp,
        fontFamily = textMedium,
    ),
    bodySmall = TextStyle(
        color = GrayDarker,
        fontSize = 18.sp,
        fontFamily = textRegular,
        letterSpacing = (-0.74).sp
    ),
)