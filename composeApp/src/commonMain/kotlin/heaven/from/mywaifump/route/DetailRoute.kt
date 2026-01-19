package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import heaven.from.model.MyWaifuModelV2
import heaven.from.mywaifump.screen.DetailScreen

@Composable
fun DetailRoute(
    waifu: MyWaifuModelV2,
    popCallback: () -> Unit
) {
    DetailScreen(
        waifu = waifu,
        popCallback = popCallback
    )
}
