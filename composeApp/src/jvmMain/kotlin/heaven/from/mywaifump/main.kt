package heaven.from.mywaifump

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.model.MyWaifuState
import heaven.from.mywaifump.provider.CoilProvider.provideCoilLogger
import heaven.from.mywaifump.provider.MyWaifuRepositoryProvider
import heaven.from.mywaifump.screen.ErrorScreen
import heaven.from.mywaifump.screen.LoadingScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

fun main() {
    // Initialise Napier.
    if (DEBUG_MODE) {
        Napier.base(DebugAntilog())
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "MyWaifuMP",
        ) {
            val myWaifuRepository = MyWaifuRepositoryProvider
                .provideRepository()
                .collectAsState(MyWaifuState.Loading)
                .value

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

            AnimatedContent(
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                targetState = myWaifuRepository
            ) { targetState ->
                when (targetState) {
                    is MyWaifuState.Loading -> LoadingScreen()
                    is MyWaifuState.Success -> MyWaifu()
                    is MyWaifuState.Error -> ErrorScreen(message = targetState.message)
                }
            }
        }
    }
}
