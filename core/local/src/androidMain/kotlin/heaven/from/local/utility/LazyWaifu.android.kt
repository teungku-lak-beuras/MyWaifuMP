package heaven.from.local.utility

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Truly a lazy one.
 *
 * Should be safe for KMP and coroutines. All later call gives no effect. Explanation below:
 *
 * 1.
 */
actual class LazyWaifu<T: Any, R: Any>(
    private val initialiser: suspend (T) -> R
) {
    private val mutex = Mutex()
    private var initialised = false
    private lateinit var value: R

    actual suspend fun get(transmitter: T): R {
        if (initialised) {
            return value
        }
        return mutex.withLock {
            if (!initialised) {
                value = initialiser(transmitter)
                initialised = true
            }
            value
        }
    }
}
