package heaven.from.mywaifump.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import heaven.from.model.ApiState
import heaven.from.model.WaifuModelV1
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.component.sizeMedium
import heaven.from.mywaifump.component.sizeSmall
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.layout.MyWaifuScaffold
import heaven.from.mywaifump.layout.MyWaifuSwitchingTopAppBar
import heaven.from.mywaifump.utility.LocalWindowSize
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.about
import mywaifump.composeapp.generated.resources.app_name
import mywaifump.composeapp.generated.resources.error
import mywaifump.composeapp.generated.resources.error_bug
import mywaifump.composeapp.generated.resources.help
import mywaifump.composeapp.generated.resources.info
import mywaifump.composeapp.generated.resources.interrogation
import mywaifump.composeapp.generated.resources.loading
import mywaifump.composeapp.generated.resources.picture
import mywaifump.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import plus

@Composable
fun Dropdown(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit
) {
    DropdownMenu(
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        shape = RoundedCornerShape(sizeMedium),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        val menuItemSize = sizeSmall + sizeMedium

        DropdownMenuItem(
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(menuItemSize),
                    contentDescription = stringResource(Res.string.help),
                    painter = painterResource(Res.drawable.interrogation)
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.help)
                )
            },
            onClick = helpCallback
        )
        HorizontalDivider()
        DropdownMenuItem(
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(menuItemSize),
                    contentDescription = stringResource(Res.string.settings),
                    painter = painterResource(Res.drawable.settings)
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.settings)
                )
            },
            onClick = settingsCallback
        )
        HorizontalDivider()
        DropdownMenuItem(
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(menuItemSize),
                    contentDescription = stringResource(Res.string.about),
                    painter = painterResource(Res.drawable.info)
                )
            },
            text = {
                Text(
                    text = stringResource(Res.string.about)
                )
            },
            onClick = aboutCallback
        )
    }
}

@Composable
fun LoadingItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(72.dp),
            contentDescription = stringResource(Res.string.loading),
            painter = painterResource(Res.drawable.picture)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(Res.string.loading)
        )
    }
}

@Composable
fun SuccessItem(
    waifu: WaifuModelV1
) {
    val sizeResolver = rememberConstraintsSizeResolver()
    val imagePainter = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalPlatformContext.current)
            .data(waifu.url)
            .build()
    )
    val state by imagePainter.state.collectAsState()
    val imageModifier = Modifier
        .height(200.dp)
        .fillMaxWidth()

    Surface(
        modifier = Modifier.clip(RoundedCornerShape(sizeMedium)),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.padding(sizeSmall)
        ) {
            Surface(
                modifier = imageModifier.clip(RoundedCornerShape(sizeSmall)),
                color = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                when (state) {
                    is AsyncImagePainter.State.Empty,
                    is AsyncImagePainter.State.Loading -> {
                        Column(
                            modifier = Modifier.padding(sizeMedium),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            LoadingItem()
                            CircularProgressIndicator()
                        }
                    }
                    is AsyncImagePainter.State.Success -> {
                        Image(
                            modifier = sizeResolver,
                            painter = imagePainter,
                            contentDescription = "Waifu artist's name: ${waifu.artistName}",
                            contentScale = ContentScale.Crop
                        )
                    }
                    is AsyncImagePainter.State.Error -> {
                        Column(
                            modifier = Modifier.padding(sizeMedium),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ErrorItem()
                        }
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = sizeSmall),
                style = MaterialTheme.typography.titleSmall,
                text = waifu.artistName
            )
            Text(
                modifier = Modifier.padding(top = sizeSmall),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = "Waifu taken from: ${waifu.sourceUrl}"
            )
        }
    }
}

@Composable
fun ErrorItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(72.dp),
            contentDescription = stringResource(Res.string.error),
            painter = painterResource(Res.drawable.error_bug)
        )
        Text(
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(Res.string.error)
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    waifu: ApiState<List<WaifuModelV1>>,
) {
    val modifier = Modifier.fillMaxSize()

    when (waifu) {
        is ApiState.Loading -> {
            Column(
                modifier = modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoadingItem()
            }
        }
        is ApiState.Success -> {
            LazyVerticalGrid(
                modifier = modifier,
                columns = GridCells.Adaptive(128.dp),
                contentPadding = paddingValues + PaddingValues(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = waifu.data
                ) { item ->
                    SuccessItem(waifu = item)
                }
            }
        }
        is ApiState.Error -> {
            Column(
                modifier = modifier.padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ErrorItem()
            }
        }
    }
}

@Composable
fun HomeScreen(
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit,
    waifu: ApiState<List<WaifuModelV1>>,
) {
    val windowSizeClass = LocalWindowSize.current
    var topAppBarExpanded by remember { mutableStateOf(true) }
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdown = @Composable {
        AnimatedVisibility(
            visible = dropdownExpanded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Dropdown(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                helpCallback = { dropdownExpanded = false },
                settingsCallback = { dropdownExpanded = false },
                aboutCallback = { dropdownExpanded = false },
            )
        }
    }

    when (windowSizeClass) {
        WindowSize.Medium -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuSwitchingTopAppBar(
                        expanded = topAppBarExpanded,
                        expandedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                collapseCallback = { topAppBarExpanded = false },
                                notificationCallback = {},
                                searchCallback = { text -> },
                                burgerCallback = { dropdownExpanded = true },
                                burgerContent = dropdown
                            )
                        },
                        collapsedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                expandCallback = { topAppBarExpanded = true },
                                notificationCallback = {},
                                burgerCallback = { dropdownExpanded = true },
                                burgerContent = dropdown
                            )
                        },
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues,
                    waifu = waifu
                )
            }
        }

        else -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuSwitchingTopAppBar(
                        expanded = topAppBarExpanded,
                        expandedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                collapseCallback = { topAppBarExpanded = false },
                                notificationCallback = {},
                                searchCallback = { text -> },
                                burgerCallback = { dropdownExpanded = true },
                                burgerContent = dropdown
                            )
                        },
                        collapsedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                expandCallback = { topAppBarExpanded = true },
                                notificationCallback = {},
                                burgerCallback = { dropdownExpanded = true },
                                burgerContent = {

                                }
                            )
                        }
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
fun HomeScreenPreview1() {
    MyWaifuPreview {
        HomeScreen(
            helpCallback = {},
            settingsCallback = {},
            aboutCallback = {},
            waifu = ApiState.Success(
                data = listOf(
                    WaifuModelV1(
                        artistName = "Yagen",
                        artistHref = "https://www.pixiv.net/en/users/39846570",
                        sourceUrl = "https://www.pixiv.net/en/artworks/128662564",
                        url = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png"
                    ),
                    WaifuModelV1(
                        artistName = "Yagen",
                        artistHref = "https://www.pixiv.net/en/users/39846570",
                        sourceUrl = "https://www.pixiv.net/en/artworks/128662564",
                        url = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png"
                    )
                )
            )
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview2() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        HomeScreen(
            helpCallback = {},
            settingsCallback = {},
            aboutCallback = {},
            waifu = ApiState.Success(
                data = listOf(
                    WaifuModelV1(
                        artistName = "Yagen",
                        artistHref = "https://www.pixiv.net/en/users/39846570",
                        sourceUrl = "https://www.pixiv.net/en/artworks/128662564",
                        url = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png"
                    ),
                    WaifuModelV1(
                        artistName = "Yagen",
                        artistHref = "https://www.pixiv.net/en/users/39846570",
                        sourceUrl = "https://www.pixiv.net/en/artworks/128662564",
                        url = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png"
                    )
                )
            )
        )
    }
}
