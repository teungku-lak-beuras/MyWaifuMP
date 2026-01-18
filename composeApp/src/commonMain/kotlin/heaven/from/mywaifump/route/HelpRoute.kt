package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import heaven.from.mywaifump.screen.HelpScreen

@Composable
fun HelpRoute(
    popCallback: () -> Unit
) {
    // val viewModel: add later
    HelpScreen(
        popCallback = popCallback
    )
}
