package heaven.from.mywaifump

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.compose.MyWaifuTheme
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.navigation.MyWaifuNavigation
import heaven.from.mywaifump.utility.provideWindowSizeClass
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MyWaifu() {
    val windowSizeClass = provideWindowSizeClass()
    val windowSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Medium -> WindowSize.Medium
        WindowWidthSizeClass.Expanded -> WindowSize.Expanded
        else -> WindowSize.Compact
    }

    CompositionLocalProvider(LocalWindowSize provides windowSize) {
        MyWaifuTheme {
            MyWaifuNavigation()
        }
    }
}
