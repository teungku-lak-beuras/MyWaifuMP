package heaven.from.mywaifump

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.mywaifump.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // Initialise Napier.
    if (DEBUG_MODE) {
        Napier.base(DebugAntilog())
    }

    // Initialise Koin.
    initKoin()

    ComposeViewport {
        MyWaifu()
    }
}