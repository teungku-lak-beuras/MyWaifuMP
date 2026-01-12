package heaven.from.mywaifump.utility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.compose.MyWaifuTheme
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize

@Composable
fun MyWaifuPreview(
    windowSize: WindowSize = WindowSize.Compact,
    content: @Composable () -> Unit
) {
    MyWaifuTheme {
        CompositionLocalProvider(LocalWindowSize provides windowSize) {
            content.invoke()
        }
    }
}
