package karpiuk.ivan.ui

import androidx.annotation.IntegerRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.sp


@Composable
@ReadOnlyComposable
fun fontSizeResource(@IntegerRes id: Int) = integerResource(id = id).sp