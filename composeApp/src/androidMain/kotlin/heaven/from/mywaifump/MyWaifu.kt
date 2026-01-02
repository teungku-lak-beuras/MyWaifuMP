package heaven.from.mywaifump

import android.app.Application
import heaven.from.buildconfig.DEBUG_MODE
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class MyWaifuApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialise Nappier.
        if (DEBUG_MODE) {
            Napier.base(DebugAntilog())
        }
    }
}
