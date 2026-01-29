package heaven.from.mywaifump.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.component.MyWaifuFloatingAppBar
import heaven.from.mywaifump.component.MyWaifuSideAppBar
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.constant.WindowSize
import heaven.from.mywaifump.constant.sizeLarger
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.utility.MyWaifuPreview

@Composable
fun MyWaifuCompactScaffold(
    topAppBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var spacerHeight by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content.invoke(
                PaddingValues(
                    top = with (LocalDensity.current) {
                        spacerHeight.toDp()
                    }
                )
            )
        }
        Box(
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    spacerHeight = layoutCoordinates.size.height
                }
        ) {
            topAppBar.invoke()
        }
    }
}

@Composable
fun MyWaifuMediumScaffold(
    sideAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Surface(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .windowInsetsPadding(WindowInsets.displayCutout)
                .windowInsetsPadding(WindowInsets.navigationBars),
            color = MaterialTheme.colorScheme.surface
        ) {
            Row {
                sideAppBar.invoke()
                content.invoke()
            }
        }
    }
}

@Composable
fun MyWaifuExpandedScaffold(
    floatingAppBar: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    var spacerHeight by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.displayCutout)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content.invoke(
                PaddingValues(
                    top = with (LocalDensity.current) {
                        spacerHeight.toDp()
                    }
                )
            )
        }
        Box(
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    spacerHeight = layoutCoordinates.size.height
                }
                .padding(sizeMedium),
            contentAlignment = Alignment.TopCenter
        ) {
            floatingAppBar.invoke()
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun MyWaifuCompactScaffoldPreview() {
    MyWaifuPreview {
        MyWaifuCompactScaffold(
            topAppBar = {
                MyWaifuTopAppBar(
                    title = "Administrator",
                    leadingTitle = "Welcome",
                    searchState = rememberTextFieldState(),
                    collapseCallback = {},
                    notificationCallback = {},
                    burgerCallback = {},
                    searchCallback = {},
                    burgerContent = {}
                )
            },
            content = { paddingValues ->
                Text(
                    modifier = Modifier
                        .padding(paddingValues),
                    text = "Hello world!"
                )
            }
        )
    }
}

@Preview(
    device = Devices.TABLET,
    showSystemUi = true
)
@Composable
private fun MyWaifuMediumScaffoldPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        MyWaifuMediumScaffold(
            sideAppBar = {
                MyWaifuSideAppBar(
                    burgerCallback = {},
                    notificationCallback = {},
                    searchCallback = {},
                    burgerContent = {}
                )
            }
        ) {
            Text("content goes here")
        }
    }
}

@Preview(
    device = Devices.DESKTOP,
    showSystemUi = true
)
@Composable
private fun MyWaifuExpandedScaffoldPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Expanded
    ) {
        MyWaifuExpandedScaffold(
            floatingAppBar = {
                MyWaifuFloatingAppBar(
                    title = "Administrator",
                    leadingTitle = "Welcome",
                    searchState = rememberTextFieldState(),
                    notificationCallback = {},
                    burgerCallback = {},
                    searchCallback = {},
                    burgerContent = {}
                )
            }
        ) { paddingValues ->
            Text(
                modifier = Modifier.padding(paddingValues),
                text = "content goes here"
            )
        }
    }
}
