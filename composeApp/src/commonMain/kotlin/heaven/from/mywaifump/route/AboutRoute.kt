package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import heaven.from.mywaifump.screen.AboutScreen

@Composable
fun AboutRoute(
    popCallback: () -> Unit
) {
    AboutScreen(
        popCallback = popCallback
    )
}
