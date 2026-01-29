package heaven.from.mywaifump.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.constant.sizeAppBarMenu
import heaven.from.mywaifump.constant.sizeLarge
import heaven.from.mywaifump.constant.sizeLarger
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.arrow_small_left
import mywaifump.composeapp.generated.resources.back
import mywaifump.composeapp.generated.resources.bell
import mywaifump.composeapp.generated.resources.menu_burger
import mywaifump.composeapp.generated.resources.more_menu
import mywaifump.composeapp.generated.resources.notification
import mywaifump.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MyWaifuFloatingAppBar(
    title: String,
    popCallback: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    size = sizeLarger
                )
            ),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(
            modifier = Modifier.padding(sizeLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyWaifuAppBarMenu(
                isTheLastMenu = false,
                onClickCallback = popCallback
            ) {
                Icon(
                    contentDescription = stringResource(Res.string.back),
                    painter = painterResource(Res.drawable.arrow_small_left)
                )
            }
            Text(
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                text = title
            )
            // Replace this ghost layout later with your preferred menu.
            Box(
                modifier = Modifier.size(sizeAppBarMenu)
            ) {}
        }
    }
}

@Composable
private fun MyWaifuMPFloatingAppBar(
    title: String,
    leadingTitle: String?,
    searchState: TextFieldState?,
    notificationCallback: (() -> Unit)?,
    searchCallback: (() -> Unit)?,
    burgerCallback: (() -> Unit)?,
    burgerContent: (@Composable () -> Unit)?
) {
    // --- Container ---
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    size = sizeLarger
                )
            ),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        // --- Container Layout ---
        Row(
            modifier = Modifier.padding(sizeLarge),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Title and leading title ---
            Column(
                modifier = Modifier.weight(3.0f)
            ) {
                if (leadingTitle != null) {
                    Text(
                        style = MaterialTheme.typography.titleSmall,
                        text = leadingTitle
                    )
                }
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = title
                )
            }
            // --- Search field ---
            if (searchCallback != null && searchState != null) {
                val primaryColor = MaterialTheme.colorScheme.primaryContainer
                val secondaryColor = MaterialTheme.colorScheme.secondaryContainer
                val tertiaryColor = MaterialTheme.colorScheme.tertiaryContainer
                val textFieldTextStyle = remember {
                    Brush.linearGradient(
                        colors = listOf(
                            primaryColor,
                            secondaryColor,
                            tertiaryColor
                        )
                    )
                }

                TextField(
                    modifier = Modifier
                        .height(sizeAppBarMenu)
                        .weight(6.0f)
                        .clip(RoundedCornerShape(sizeMedium))
                        .onKeyEvent { keyEvent ->
                            /**
                             * If key is released (KeyUp) and the key is Enter key, do something
                             * and return true to consume this event. Else, return false to
                             * yield this event to somewhere else (propagate).
                             */
                            if (keyEvent.type == KeyEventType.KeyUp &&
                                keyEvent.key == Key.Enter) {
                                searchCallback.invoke()
                                true
                            }
                            else {
                                false
                            }
                        }
                        /**
                         * Do not delete this focusable modifier. If you delete this, the IME
                         * will be shown everytime the screen is launches for the first time. I
                         * don't know why.
                         */
                        .focusable(interactionSource = remember { MutableInteractionSource() }),
                    contentPadding = PaddingValues(
                        start = sizeMedium,
                        end = sizeMedium
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    trailingIcon = {
                        Icon(
                            modifier = Modifier.padding(sizeMedium),
                            contentDescription = stringResource(Res.string.search),
                            painter = painterResource(Res.drawable.search)
                        )
                    },
                    placeholder = {
                        Text("Search...")
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(
                        brush = textFieldTextStyle
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Search
                    ),
                    onKeyboardAction = { searchCallback.invoke() },
                    state = searchState,
                )
            }
            // --- Menus ---
            Row(
                modifier = Modifier.weight(3.0f),
                horizontalArrangement = Arrangement.End
            ) {
                if (notificationCallback != null) {
                    MyWaifuAppBarMenu(
                        isTheLastMenu = false,
                        onClickCallback = notificationCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.notification),
                            painter = painterResource(Res.drawable.bell)
                        )
                    }
                }
                if (burgerCallback != null) {
                    MyWaifuAppBarMenu(
                        onClickCallback = burgerCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.more_menu),
                            painter = painterResource(Res.drawable.menu_burger)
                        )
                    }
                }
                // DO NOT simplify. Consistencies matter.
                if (burgerContent != null) {
                    burgerContent.invoke()
                }
            }
        }
    }
}

@Preview(
    device = Devices.DESKTOP
)
@Composable
private fun MyWaifuFloatingAppBarPreview3() {
    MyWaifuPreview {
        MyWaifuFloatingAppBar(
            title = "Administrator",
            popCallback = {}
        )
    }
}

// --- With leading title ---
@Composable
fun MyWaifuFloatingAppBar(
    title: String,
    leadingTitle: String,
    searchState: TextFieldState,
    notificationCallback: () -> Unit,
    searchCallback: () -> Unit,
    burgerCallback: () -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPFloatingAppBar(
        title = title,
        leadingTitle = leadingTitle,
        searchState = searchState,
        notificationCallback = notificationCallback,
        searchCallback = searchCallback,
        burgerCallback = burgerCallback,
        burgerContent = burgerContent
    )
}

// --- Without leading title
@Composable
fun MyWaifuFloatingAppBar(
    title: String,
    searchState: TextFieldState,
    notificationCallback: () -> Unit,
    searchCallback: () -> Unit,
    burgerCallback: () -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPFloatingAppBar(
        title = title,
        leadingTitle = null,
        searchState = searchState,
        notificationCallback = notificationCallback,
        searchCallback = searchCallback,
        burgerCallback = burgerCallback,
        burgerContent = burgerContent
    )
}

@Preview(
    device = Devices.DESKTOP
)
@Composable
private fun MyWaifuFloatingAppBarPreview1()  {
    MyWaifuPreview {
        MyWaifuFloatingAppBar(
            title = "Administrator",
            leadingTitle = "Welcome,",
            searchState = rememberTextFieldState(),
            notificationCallback = {},
            searchCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}

@Preview(
    device = Devices.DESKTOP
)
@Composable
private fun MyWaifuFloatingAppBarPreview2()  {
    MyWaifuPreview {
        MyWaifuFloatingAppBar(
            title = "Administrator",
            searchState = rememberTextFieldState(),
            notificationCallback = {},
            searchCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}
