package heaven.from.mywaifump

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import heaven.from.buildconfig.DEBUG_MODE
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() {
    // Initialise Napier.
    if (DEBUG_MODE) {
        Napier.base(DebugAntilog())
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MyWaifuMP",
        ) {
            MyWaifu()
        }
    }
}
