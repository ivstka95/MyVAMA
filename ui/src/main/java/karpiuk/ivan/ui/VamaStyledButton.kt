package karpiuk.ivan.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import karpiuk.ivan.ui.theme.Blue

val buttonLetterSpacing = (-0.64).sp

@Composable
fun VamaStyledButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Blue),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.horizontal_button_padding)),
        modifier = Modifier.height(dimensionResource(id = R.dimen.button_height))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}