package heaven.from.mywaifump.composition_provider

import androidx.compose.runtime.staticCompositionLocalOf
import heaven.from.mywaifump.constant.WindowSize

val LocalWindowSize = staticCompositionLocalOf<WindowSize> {
    error("WindowSizeClass isn't yet prepared.")
}
