package karpiuk.ivan.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AutosizedImage(imageUrl: String, modifier: Modifier) {
    BoxWithConstraints(modifier = modifier) {
        println("AutosizedImage $imageUrl $constraints")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl.applySize(IntSize(constraints.maxWidth, constraints.maxHeight)))
                .crossfade(true)
                .build(),
            contentDescription = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
        )
    }
}