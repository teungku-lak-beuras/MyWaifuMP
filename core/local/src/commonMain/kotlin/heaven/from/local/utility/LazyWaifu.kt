package heaven.from.local.utility

expect class LazyWaifu<T: Any, R: Any> {
    suspend fun get(transmitter: T): R
}
