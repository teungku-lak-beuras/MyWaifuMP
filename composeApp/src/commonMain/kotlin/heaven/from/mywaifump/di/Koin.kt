package heaven.from.mywaifump.di

import heaven.from.buildconfig.DEBUG_MODE
import io.github.aakira.napier.Napier
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.ksp.generated.startKoin

private fun provideKoinLogger(): Logger {
    return object : Logger() {
        private val tag = "Koin"

        override fun display(level: Level, msg: MESSAGE) {
            when (level) {
                Level.DEBUG -> Napier.d(tag = tag, message = msg)
                Level.INFO -> Napier.i(tag = tag, message = msg)
                Level.WARNING -> Napier.w(tag = tag, message = msg)
                Level.ERROR -> Napier.e(tag = tag, message = msg)
                Level.NONE -> {}
            }
        }
    }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    MyWaifuApp.startKoin {
        includes(config)

        if (DEBUG_MODE) {
            logger(provideKoinLogger())
        }

//        modules(MyWaifuModule().module)
    }
}
