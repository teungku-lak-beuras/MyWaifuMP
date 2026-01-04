package heaven.from.network.provider

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

const val CONNECT_TIMEOUT: Long = 5_000
const val REQUEST_TIMEOUT: Long = 15_000
const val SOCKET_TIMEOUT: Long = REQUEST_TIMEOUT

expect object NekosBestApiProvider {
    fun provideEngine(): HttpClientEngineFactory<HttpClientEngineConfig>

    fun provideHttpClient(
        engine: HttpClientEngineFactory<HttpClientEngineConfig>
    ): HttpClient
}
