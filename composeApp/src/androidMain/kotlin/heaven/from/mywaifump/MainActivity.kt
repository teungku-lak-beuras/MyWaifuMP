package heaven.from.mywaifump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import heaven.from.mywaifump.provider.RepositoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val myWaifuAppVisible by remember { mutableStateOf(false) }

            when (RepositoryProvider
                .initialiseMyWaifuRoomDao(this)
                .flowOn(Dispatchers.IO)
                .collectAsStateWithLifecycle(true)
                .value
            ) {
                true -> {
                    AnimatedVisibility(
                        visible = myWaifuAppVisible
                    ) {
                        MyWaifuLaunchScreen()
                    }
                }
                false -> {
                    AnimatedVisibility(
                        visible = myWaifuAppVisible
                    ) {
                        MyWaifu()
                    }
                }
            }
        }
    }
}
