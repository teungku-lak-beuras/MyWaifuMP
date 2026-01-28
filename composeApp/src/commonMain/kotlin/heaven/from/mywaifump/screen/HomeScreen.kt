package heaven.from.mywaifump.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import heaven.from.model.MyWaifuModelV2
import heaven.from.model.MyWaifuState
import heaven.from.mywaifump.component.MyWaifuSideAppBar
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.composition_provider.LocalWindowSize
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.constant.sizeSmall
import heaven.from.mywaifump.layout.MyWaifuScaffold
import heaven.from.mywaifump.layout.MyWaifuSwitchingTopAppBar
import heaven.from.mywaifump.utility.MyWaifuPreview
import heaven.from.mywaifump.utility.plus
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
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

@Composable
private fun Dropdown(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit
) {
    DropdownMenu(
        containerColor = MaterialTheme.colorScheme.surface,
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
            modifier = Modifier.padding(sizeSmall),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(Res.string.loading)
        )
    }
}

@Composable
private fun SuccessItem(
    waifu: MyWaifuModelV2,
    detailCallback: (MyWaifuModelV2) -> Unit,
) {
    val imageRequest = ImageRequest
        .Builder(LocalPlatformContext.current)
        .data(waifu.cdnCompressedImageUrl)
    val imagePainter = rememberAsyncImagePainter(
        model = imageRequest
            // Deleting this will severely hurt performance.
            .size(width = 300, height = Int.MAX_VALUE)
            .build()
    )
    val imagePainterState by imagePainter.state.collectAsState()
    val interactionSource = remember { MutableInteractionSource() }
    var isLongPressed by remember { mutableStateOf(false) }

    // This mutable variable will be handled within a coroutine because a pop up's appearance is
    // instant.
    var popUpVisible by remember { mutableStateOf(false) }
    // The offset of the composable component which receives pointerInput() modifier.
    var pointerContainerOffset by remember { mutableStateOf(Offset.Zero) }
    // Pointer offset relative to the above composable component's zero offset.
    var pointerOffset by remember { mutableStateOf(Offset.Zero) }
    // The size of the preview image within the pop up upon long press.
    var peekImageSize by remember { mutableStateOf(IntSize.Zero) }
    // The animation keyframe for the preview image within the pop up upon long press.
    val peekImageAnimationKeyframe = remember { Animatable(0f) }

    /**
     * Upon entering, pop up must be visible first and then animation so that the preview image
     * has a parent container. Upon exiting, animation first before disable the pop up visibility
     * so that the preview image has that exiting animation. Pop up appearance/disappearance is
     * instant.
     */
    LaunchedEffect(isLongPressed) {
        if (isLongPressed) {
            popUpVisible = true
            peekImageAnimationKeyframe.animateTo(
                targetValue = 1f,
                animationSpec = spring()
            )
        }
        else {
            peekImageAnimationKeyframe.animateTo(
                targetValue = 0f,
                animationSpec = spring()
            )
            popUpVisible = false
        }
    }
    if (popUpVisible) {
        val currentWindowWidth = LocalWindowInfo.current.containerSize.width
        val currentWindowHeight = LocalWindowInfo.current.containerSize.height
        var offsetX: Dp
        var offsetY: Dp

        with(LocalDensity.current) {
            /**
             * 1. Pointer offset + pointer container offset = pointer offset within the entire
             *    window.
             * 2. Subtract offset X with half of the preview image so that the image alignment is
             *    horizontally centered relative to the pointer.
             * 3. Subtract offset Y with the entire preview image height so that the image is
             *    located at the top of the pointer.
             * 4. Both this offset then used for the preview image offset for horizontally
             *    centered preview image at the top of the pointer.
             */
            offsetX = (pointerOffset.x + pointerContainerOffset.x - (peekImageSize.width * 0.5f))
                .coerceIn(
                    minimumValue = 0.0f,
                    maximumValue = (currentWindowWidth - peekImageSize.width).toFloat()
                )
                .toDp()
            offsetY = (pointerOffset.y + pointerContainerOffset.y - peekImageSize.height - 100)
                .coerceIn(
                    minimumValue = 0.0f,
                    maximumValue = (currentWindowHeight - peekImageSize.height).toFloat()
                )
                .toDp()
        }

        Popup {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.4f))
            ) {
                AsyncImage(
                    modifier = Modifier
                        .onGloballyPositioned { layoutCoordinates ->
                            peekImageSize = layoutCoordinates.size
                        }
                        .offset(
                            x = offsetX,
                            y = offsetY
                        )
                        .graphicsLayer {
                            scaleX = peekImageAnimationKeyframe.value
                            scaleY = peekImageAnimationKeyframe.value
                            alpha = peekImageAnimationKeyframe.value
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0.5f,
                                pivotFractionY = 1.0f
                            )
                        },
                    contentDescription = null,
                    model = imageRequest
                        .size(width = 600, height = Int.MAX_VALUE)
                        .build()
                )
            }
        }
    }

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(sizeMedium))
            .onGloballyPositioned { layoutCoordinates ->
                pointerContainerOffset = layoutCoordinates.positionInWindow()
            }
            .indication(
                interactionSource = interactionSource,
                indication = ripple(
                    color = Color.Black
                )
            )
            .hoverable(
                interactionSource = interactionSource,
                enabled = true
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { offset ->
                        val pressEvent = PressInteraction.Press(offset)

                        interactionSource.emit(pressEvent)
                        tryAwaitRelease()
                        interactionSource.emit(PressInteraction.Release(pressEvent))
                        isLongPressed = false
                    },
                    onLongPress = { offset ->
                        isLongPressed = true
                        pointerOffset = offset
                    },
                    onTap = {
                        detailCallback.invoke(waifu)
                    }
                )
            },
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Column(
            modifier = Modifier.padding(sizeSmall)
        ) {
            Surface(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(sizeSmall)),
                color = MaterialTheme.colorScheme.tertiaryContainer
            ) {
                when (imagePainterState) {
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
                            contentScale = ContentScale.Crop,
                            contentDescription = "Waifu artist's name: ${waifu.artistName}",
                            painter = imagePainter
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
                text = "Waifu taken from: ${waifu.imageSourceUrl}"
            )
        }
    }
}

@Composable
private fun ErrorItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(72.dp),
            contentDescription = stringResource(Res.string.error),
            painter = painterResource(Res.drawable.error_bug)
        )
        Text(
            modifier = Modifier.padding(sizeSmall),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(Res.string.error)
        )
    }
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    isLoadingMore: Boolean,
    isInitialyLoaded: Boolean,
    waifu: MyWaifuState<List<MyWaifuModelV2>>,
    loadMoreCallback: () -> Unit,
    detailCallback: (MyWaifuModelV2) -> Unit
) {
    val state = rememberLazyGridState()

    if (isInitialyLoaded) {
        LaunchedEffect(state) {
            snapshotFlow {
                // Last *visible* item index or -1 if empty.
                val lastVisibleItemIndex = state
                    .layoutInfo
                    .visibleItemsInfo
                    .lastOrNull()
                    ?.index ?: -1
                // Total item.
                val totalItem = state.layoutInfo.totalItemsCount

                /**
                 * Emit whether last *visible* item index is larger or equal to the *actual* last
                 * item index (item list size - 1) or not. The values emitted are ridiculously
                 * lots upon scrolling, hence the need to be filtered (code below). The U.I. needs
                 * to be noticed with the emission value only when it switches from true to false,
                 * and vice versa (whether the user has scrolled past to the bottom or not).
                 */
                lastVisibleItemIndex >= totalItem - 1
            }
                .debounce(100L)              // Delay to save energy.
                .distinctUntilChanged()      // Filter out all subsequent same emission values.
                .filter { it }               // Filter out all false values.
                .collect {                   // This block will launch only when true value emitted.
                    if (!isLoadingMore) {
                        loadMoreCallback.invoke()
                    }
                }
        }
    }

    when (waifu) {
        is MyWaifuState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LoadingItem()
            }
        }
        is MyWaifuState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(128.dp),
                contentPadding = paddingValues + PaddingValues(
                    top = sizeMedium,
                    start = sizeMedium,
                    end = sizeMedium,
                    bottom = sizeMedium
                ),
                horizontalArrangement = Arrangement.spacedBy(sizeMedium),
                verticalArrangement = Arrangement.spacedBy(sizeMedium),
                state = state
            ) {
                items(
                    key = { it.id },
                    items = waifu.data
                ) { item ->
                    SuccessItem(
                        waifu = item,
                        detailCallback = detailCallback
                    )
                }
                if (isLoadingMore) {
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
        is MyWaifuState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
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
    waifu: MyWaifuState<List<MyWaifuModelV2>>,
    isLoadingMore: Boolean,
    isInitialyLoaded: Boolean,
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit,
    loadMoreCallback: () -> Unit,
    detailCallback: (MyWaifuModelV2) -> Unit
) {
    val searchState = rememberTextFieldState()
    var topAppBarExpanded by rememberSaveable { mutableStateOf(true) }
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
                helpCallback = {
                    dropdownExpanded = false
                    helpCallback.invoke()
                },
                settingsCallback = {
                    dropdownExpanded = false
                    settingsCallback.invoke()
                },
                aboutCallback = {
                    dropdownExpanded = false
                    aboutCallback.invoke()
                },
            )
        }
    }

    /**
     * Support for different window size will be added later.
     */
    when (LocalWindowSize.current) {
        WindowSize.Compact -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuSwitchingTopAppBar(
                        expanded = topAppBarExpanded,
                        expandedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                searchState = searchState,
                                collapseCallback = { topAppBarExpanded = false },
                                notificationCallback = {},
                                searchCallback = {},
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
                    waifu = waifu,
                    isLoadingMore = isLoadingMore,
                    isInitialyLoaded = isInitialyLoaded,
                    loadMoreCallback = loadMoreCallback,
                    detailCallback = detailCallback
                )
            }
        }
        WindowSize.Medium -> {
            MyWaifuScaffold(
                sideAppBar = {
                    MyWaifuSideAppBar(
                        burgerCallback = { dropdownExpanded = true },
                        notificationCallback = {},
                        searchCallback = {},
                        burgerContent = dropdown
                    )
                }
            ) {
                Content(
                    paddingValues = PaddingValues(0.dp),
                    waifu = waifu,
                    isLoadingMore = isLoadingMore,
                    isInitialyLoaded = isInitialyLoaded,
                    loadMoreCallback = loadMoreCallback,
                    detailCallback = detailCallback
                )
            }
        }
        WindowSize.Expanded -> {
            MyWaifuScaffold(
                topAppBar = {
                    MyWaifuSwitchingTopAppBar(
                        expanded = topAppBarExpanded,
                        expandedTopAppBar = {
                            MyWaifuTopAppBar(
                                title = stringResource(Res.string.app_name),
                                searchState = searchState,
                                collapseCallback = { topAppBarExpanded = false },
                                notificationCallback = {},
                                searchCallback = {},
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
                    waifu = waifu,
                    isLoadingMore = isLoadingMore,
                    isInitialyLoaded = isInitialyLoaded,
                    loadMoreCallback = loadMoreCallback,
                    detailCallback = detailCallback
                )
            }
        }
    }
}

fun getWaifuList(): List<MyWaifuModelV2> {
    val waifuList = mutableListOf<MyWaifuModelV2>()

    for (i in 0..16) {
        waifuList.add(
            MyWaifuModelV2(
                cdnImageUrl = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png",
                cdnCompressedImageUrl = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png",
                imageSourceUrl = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png",
                directImageSourceUrl = "https://nekos.best/api/v2/waifu/5cd32e1d-351f-43c3-93ac-9f9ac51d58b1.png",
                category = "cute",
                rating = "safe",
                tags = emptyList(),
                artistName = "Yagen",
                artistUrl = "https://www.pixiv.net/en/users/39846570",
                copyright = "(c) Yagen. All rights reserved"
            )
        )
    }
    return waifuList
}

@Composable
fun HomeScreenPreview() {
    HomeScreen(
        waifu = MyWaifuState.Success(
            data = getWaifuList()
        ),
        isLoadingMore = true,
        isInitialyLoaded = true,
        helpCallback = {},
        settingsCallback = {},
        aboutCallback = {},
        loadMoreCallback = {},
        detailCallback = {}
    )
}

@Preview
@Composable
fun HomeScreenAndroidPreview() {
    MyWaifuPreview {
        HomeScreenPreview()
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun HomeScreenTabletPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        HomeScreenPreview()
    }
}

@Preview(device = Devices.DESKTOP)
@Composable
fun HomeScreenDesktopPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Expanded
    ) {
        HomeScreenPreview()
    }
}
