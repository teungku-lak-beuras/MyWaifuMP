package heaven.from.mywaifump.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import heaven.from.model.MyWaifuModelV2
import heaven.from.mywaifump.component.MyWaifuFloatingAppBar
import heaven.from.mywaifump.component.MyWaifuSideAppBar
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.layout.MyWaifuCompactScaffold
import heaven.from.mywaifump.layout.MyWaifuExpandedScaffold
import heaven.from.mywaifump.layout.MyWaifuMediumScaffold
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.error_bug
import mywaifump.composeapp.generated.resources.picture
import mywaifump.composeapp.generated.resources.waifu_detail
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
private fun Content(
    paddingValues: PaddingValues,
    waifu: MyWaifuModelV2
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        val model = ImageRequest
            .Builder(LocalPlatformContext.current)
            .data(waifu.cdnCompressedImageUrl)
            .build()
        val uriHandler = LocalUriHandler.current

        AsyncImage(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = sizeMedium,
                    start = sizeMedium,
                    end = sizeMedium
                )
                .align(Alignment.CenterHorizontally)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.inverseSurface
                ),
            contentDescription = "Waifu drawn by ${waifu.artistName}",
            placeholder = painterResource(Res.drawable.picture),
            error = painterResource(Res.drawable.error_bug),
            model = model
        )
        HorizontalDivider(
            modifier = Modifier.padding(
                top = sizeMedium,
                start = sizeMedium,
                end = sizeMedium
            )
        )
        Column(
            modifier = Modifier.padding(sizeMedium)
        ) {
            Text(
                text = waifu.artistName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "Rating: ${waifu.rating}"
            )
            Text(
                text = "Category: ${waifu.category}"
            )
            Text(
                text = "Tags: ${waifu.tags}"
            )
            Text(
                text = waifu.copyright
            )
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    uriHandler.openUri(waifu.artistUrl)
                }
            ) {
                Text("Visit artist's page")
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    uriHandler.openUri(waifu.imageSourceUrl)
                }
            ) {
                Text("Visit waifu's page")
            }
        }
    }
}

@Composable
fun DetailScreen(
    waifu: MyWaifuModelV2,
    popCallback: () -> Unit
) {
    when (LocalWindowSize.current) {
        WindowSize.Compact -> {
            MyWaifuCompactScaffold(
                topAppBar = {
                    MyWaifuTopAppBar(
                        title = stringResource(Res.string.waifu_detail),
                        popCallback = popCallback
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues,
                    waifu = waifu
                )
            }
        }
        WindowSize.Medium -> {
            MyWaifuMediumScaffold(
                sideAppBar = {
                    MyWaifuSideAppBar(
                        popCallback = popCallback
                    )
                }
            ) {
                Content(
                    paddingValues = PaddingValues(sizeMedium),
                    waifu = waifu
                )
            }
        }
        WindowSize.Expanded -> {
            MyWaifuExpandedScaffold(
                floatingAppBar = {
                    MyWaifuFloatingAppBar(
                        title = stringResource(Res.string.waifu_detail),
                        popCallback = popCallback
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues,
                    waifu = waifu
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview1() = MyWaifuPreview {
    DetailScreen(
        waifu = MyWaifuModelV2(
            "bruh",
            "bruh",
            "bruh",
            "bruh",
            "bruh",
            "bruh",
            "bruh",
            listOf("bruh", "bruh", "bruh"),
            "bruh",
            "bruh",
            "(c) bruh bruhbruh bruhs"
        ),
        popCallback = {}
    )
}
