package heaven.from.mywaifump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.mywaifump.provider.CoilProvider.provideCoilLogger
import heaven.from.mywaifump.provider.MyWaifuRepositoryProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MyWaifuRepositoryProvider.provideRepository(applicationContext)

            // Set Coil 3 image loader.
            setSingletonImageLoaderFactory { context ->
                ImageLoader
                    .Builder(context)
                    .components {
                        add(
                            KtorNetworkFetcherFactory(
                                httpClient = HttpClient(CIO)
                            )
                        )
                    }
                    .logger(if (DEBUG_MODE) provideCoilLogger() else null)
                    .build()
            }

            MyWaifu()
        }
    }
}
