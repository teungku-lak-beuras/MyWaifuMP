package heaven.from.mywaifump.utility

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun provideWindowSizeClass(): WindowSizeClass {
    val height = with (LocalDensity.current) { LocalWindowInfo.current.containerSize.height.toDp() }
    val width = with (LocalDensity.current) { LocalWindowInfo.current.containerSize.width.toDp() }

    return WindowSizeClass.calculateFromSize(DpSize(width, height))
}
