package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import heaven.from.mywaifump.screen.SettingsScreen

@Composable
fun SettingsRoute(
    popCallback: () -> Unit
) {
    SettingsScreen(
        popCallback = popCallback
    )
}
