package heaven.from.mywaifump.route

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import heaven.from.model.MyWaifuModelV2
import heaven.from.model.MyWaifuState
import heaven.from.mywaifump.provider.MyWaifuRepositoryProvider
import heaven.from.mywaifump.screen.ErrorScreen
import heaven.from.mywaifump.screen.HomeScreen
import heaven.from.mywaifump.screen.LoadingScreen
import heaven.from.mywaifump.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit,
    detailCallback: (MyWaifuModelV2) -> Unit
) {
    AnimatedContent(
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        targetState = MyWaifuRepositoryProvider.repository.collectAsStateWithLifecycle().value
    ) { targetState ->
        when (targetState) {
            is MyWaifuState.Loading -> LoadingScreen()
            is MyWaifuState.Success -> {
                val viewModel = viewModel {
                    HomeViewModel(targetState.data)
                }

                HomeScreen(
                    waifu = viewModel.nekosiaCatWaifu.collectAsState().value,
                    isLoadingMore = viewModel.isLoadingMore,
                    isInitialyLoaded = viewModel.isInitialyLoaded,
                    helpCallback = helpCallback,
                    settingsCallback = settingsCallback,
                    aboutCallback = aboutCallback,
                    loadMoreCallback = { viewModel.getMoreNekosiaCatWaifu() },
                    detailCallback = detailCallback
                )
            }
            is MyWaifuState.Error -> ErrorScreen(message = targetState.message)
        }
    }
}
