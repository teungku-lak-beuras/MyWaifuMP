package heaven.from.mywaifump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.crossfade
import heaven.from.buildconfig.DEBUG_MODE
import heaven.from.model.MyWaifuState
import heaven.from.mywaifump.provider.CoilProvider.provideCoilLogger
import heaven.from.mywaifump.provider.MyWaifuRepositoryProvider
import heaven.from.mywaifump.screen.ErrorScreen
import heaven.from.mywaifump.screen.LoadingScreen
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            var loading by remember { mutableStateOf(true) }
            var success by remember { mutableStateOf(false) }
            var error by remember { mutableStateOf(false) }
            var errorMessage: String = "Initial error message."
            val myWaifuRepository = MyWaifuRepositoryProvider
                .provideRepository(this)
                .collectAsStateWithLifecycle(MyWaifuState.Loading)
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

            when (myWaifuRepository) {
                // True should be last so that any remaining content visible will disappear first.
                is MyWaifuState.Loading -> {
                    success = false
                    error = false
                    loading = true
                }
                is MyWaifuState.Success -> {
                    loading = false
                    error = false
                    success = true
                }
                is MyWaifuState.Error -> {
                    loading = false
                    success = false
                    error = true
                    errorMessage = myWaifuRepository.message
                }
            }

            AnimatedVisibility(
                visible = loading,
                enter = scaleIn(
                    animationSpec = spring()
                ),
                exit = scaleOut(
                    animationSpec = spring()
                )
            ) {
                LoadingScreen()
            }
            AnimatedVisibility(
                visible = success,
                enter = scaleIn(
                    animationSpec = spring()
                ),
                exit = scaleOut(
                    animationSpec = spring()
                )
            ) {
                MyWaifu()
            }
            AnimatedVisibility(
                visible = error
                ,
                enter = scaleIn(
                    animationSpec = spring()
                ),
                exit = scaleOut(
                    animationSpec = spring()
                )
            ) {
                ErrorScreen(message = errorMessage)
            }
        }
    }
}
