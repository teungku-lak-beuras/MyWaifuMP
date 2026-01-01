package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import heaven.from.mywaifump.screen.HomeScreen
import heaven.from.mywaifump.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeRoute(
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit
) {
    val viewModel = koinViewModel<HomeViewModel>()

    HomeScreen(
        helpCallback = helpCallback,
        settingsCallback = settingsCallback,
        aboutCallback = aboutCallback,
        waifu = viewModel.waifu.collectAsState().value
    )
}
