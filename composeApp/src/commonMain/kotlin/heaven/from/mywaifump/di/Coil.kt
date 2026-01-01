package heaven.from.mywaifump.di

import coil3.util.Logger
import io.github.aakira.napier.Napier

fun provideCoilLogger(): Logger {
    return object : Logger {
        override var minLevel: Logger.Level = Logger.Level.Info

        override fun log(
            tag: String,
            level: Logger.Level,
            message: String?,
            throwable: Throwable?
        ) {
            when (level) {
                Logger.Level.Verbose -> Napier.v(tag = tag, message = message.toString())
                Logger.Level.Debug -> Napier.d(tag = tag, message = message.toString())
                Logger.Level.Info -> Napier.i(tag = tag, message = message.toString())
                Logger.Level.Warn -> Napier.w(tag = tag, message = message.toString())
                Logger.Level.Error -> Napier.e(tag = tag, message = message.toString())
            }
        }
    }
}
