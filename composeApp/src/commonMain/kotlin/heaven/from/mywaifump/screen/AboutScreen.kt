package heaven.from.mywaifump.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.component.MyWaifuSideAppBar
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.layout.MyWaifuScaffold
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.about_1
import mywaifump.composeapp.generated.resources.about_2
import mywaifump.composeapp.generated.resources.about_3
import mywaifump.composeapp.generated.resources.about_4
import mywaifump.composeapp.generated.resources.nekos_best_api
import mywaifump.composeapp.generated.resources.nekos_best_api_website_image
import mywaifump.composeapp.generated.resources.nekosia_cat_api
import mywaifump.composeapp.generated.resources.nekosia_cat_api_website_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
private fun Content(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = sizeMedium,
                    start = sizeMedium,
                    end = sizeMedium
                ),
            style = MaterialTheme.typography.titleLarge,
            text = "Hi, there! :)"
        )
        Column(
            modifier = Modifier.padding(sizeMedium)
        ) {
            Text(
                textAlign = TextAlign.Justify,
                text = stringResource(Res.string.about_1)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(Res.string.nekos_best_api)
            )
            Image(
                modifier = Modifier.padding(top = sizeMedium),
                contentDescription = stringResource(Res.string.nekos_best_api_website_image),
                painter = painterResource(Res.drawable.nekos_best_api)
            )
            Text(
                modifier = Modifier.padding(top = sizeMedium),
                textAlign = TextAlign.Justify,
                text = stringResource(Res.string.about_2)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(Res.string.nekosia_cat_api)
            )
            Image(
                modifier = Modifier.padding(top = sizeMedium),
                contentDescription = stringResource(Res.string.nekosia_cat_api_website_image),
                painter = painterResource(Res.drawable.nekosia_cat_api)
            )
            Text(
                modifier = Modifier.padding(top = sizeMedium),
                textAlign = TextAlign.Justify,
                text = stringResource(Res.string.about_3)
            )
            HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
            Text(
                textAlign = TextAlign.Justify,
                text = stringResource(Res.string.about_4)
            )
        }
    }
}

@Composable
fun AboutScreen(
    popCallback: () -> Unit
) {
    when (LocalWindowSize.current) {
        WindowSize.Compact -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuTopAppBar(
                        title = "About",
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
private fun AboutScreenPreview() {
    AboutScreen(
        popCallback = {}
    )
}

@Preview
@Composable
private fun AboutScreenAndroidPreview() {
    MyWaifuPreview {
        AboutScreenPreview()
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun AboutScreenTabletPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        AboutScreenPreview()
    }
}

@Preview(device = Devices.DESKTOP)
@Composable
private fun AboutScreenDesktopPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Expanded
    ) {
        AboutScreenPreview()
    }
}
