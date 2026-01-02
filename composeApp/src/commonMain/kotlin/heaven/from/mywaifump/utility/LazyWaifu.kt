package heaven.from.mywaifump.utility

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class LazyWaifu<T: Any, R: Any>(
    private val initialiser: suspend (T) -> R
) {
    private val mutex = Mutex()
    private var initialised = false
    private lateinit var value: R

    suspend fun get(transmitter: T): R {
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
