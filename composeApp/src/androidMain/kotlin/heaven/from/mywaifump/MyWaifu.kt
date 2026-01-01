package heaven.from.mywaifump

import android.app.Application
import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.mywaifump.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext

class MyWaifuApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialise Nappier.
        if (DEBUG_MODE) {
            Napier.base(DebugAntilog())
        }

        // Initialise Koin.
        initKoin {
            androidContext(this@MyWaifuApplication)
        }
    }
}
