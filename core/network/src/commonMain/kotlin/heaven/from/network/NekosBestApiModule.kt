package heaven.from.network

import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.buildconfig.NEKOS_BEST_API
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.CIOEngineConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

const val CONNECT_TIMEOUT: Long = 5_000
const val REQUEST_TIMEOUT: Long = 15_000
const val SOCKET_TIMEOUT: Long = REQUEST_TIMEOUT

@Module
@ComponentScan
class NekosBestApiModule {
    @Single
    fun provideEngine(): HttpClientEngineFactory<CIOEngineConfig> = CIO

    @Single
    fun provideHttpClient(
        engine: HttpClientEngineFactory<CIOEngineConfig>
    ): HttpClient {
        return HttpClient(engineFactory = engine) {
            defaultRequest {
                url(NEKOS_BEST_API)
            }

            if (DEBUG_MODE) {
                install(Logging) {
                    logger = object : Logger {
                        private val tag = "Ktor"

                        override fun log(message: String) {
                            Napier.d(tag = tag, message = message)
                        }
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true // Just in case of sudden API changes.
                    }
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT
                requestTimeoutMillis = REQUEST_TIMEOUT
                socketTimeoutMillis = SOCKET_TIMEOUT
            }
        }
    }
}
