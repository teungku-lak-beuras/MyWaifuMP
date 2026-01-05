package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import heaven.from.mywaifump.provider.MyWaifuRepositoryProvider
import heaven.from.mywaifump.screen.HomeScreen
import heaven.from.mywaifump.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit
) {
    val viewModel = viewModel {
        HomeViewModel(MyWaifuRepositoryProvider.provideCachedRepository())
    }

    HomeScreen(
        waifu = viewModel.waifu.collectAsState().value,
        isLoadingMore = viewModel.isLoadingMore,
        helpCallback = helpCallback,
        settingsCallback = settingsCallback,
        aboutCallback = aboutCallback,
        loadMoreCallback = { viewModel.getMoreWaifu() }
    )
}
