package heaven.from.mywaifump.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.component.MyWaifuSideAppBar
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.layout.MyWaifuScaffold
import heaven.from.mywaifump.utility.MyWaifuPreview

@Composable
private fun Content(
    paddingValues: PaddingValues
) {
    Text(
        modifier = Modifier.padding(paddingValues),
        text = "Help screen is under construction :)"
    )
}

@Composable
fun HelpScreen(
    popCallback: () -> Unit
) {
    when (LocalWindowSize.current) {
        WindowSize.Compact -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuTopAppBar(
                        title = "Help",
                        popCallback = popCallback
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues
                )
            }
        }
        WindowSize.Medium -> {
            MyWaifuScaffold(
                sideAppBar = {
                    MyWaifuSideAppBar(
                        popCallback = popCallback,
                        notificationCallback = {}
                    )
                }
            ) {
                Content(
                    paddingValues = PaddingValues(sizeMedium)
                )
            }
        }
        WindowSize.Expanded -> {
            MyWaifuScaffold(
                sideAppBar = {
                    MyWaifuSideAppBar(
                        popCallback = popCallback,
                        notificationCallback = {}
                    )
                }
            ) {
                Content(
                    paddingValues = PaddingValues(sizeMedium)
                )
            }
        }
    }
}

@Composable
private fun HelpScreenPreview() {
    HelpScreen(
        popCallback = {}
    )
}

@Preview
@Composable
private fun HelpScreenAndroidPreview() {
    MyWaifuPreview {
        HelpScreenPreview()
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun HelpScreenTabletPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        HelpScreenPreview()
    }
}

@Preview(device = Devices.DESKTOP)
@Composable
private fun HelpScreenDesktopPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Expanded
    ) {
        HelpScreenPreview()
    }
}
