/**
 * This code contains nullable values that were thoroughly considered.
 * The nullable behaviour is internal to this code only, safe to
 * outsider codes.
 *
 * (c) 2025 ZeEFS. All rights reserved.
 */

package heaven.from.mywaifump.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import heaven.from.mywaifump.constant.shape
import heaven.from.mywaifump.constant.topAppBarMenuSize
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.arrow_small_left
import mywaifump.composeapp.generated.resources.bell
import mywaifump.composeapp.generated.resources.caret_down
import mywaifump.composeapp.generated.resources.caret_up
import mywaifump.composeapp.generated.resources.menu_burger
import mywaifump.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Muvara.
 */
@Composable
private fun MyWaifuMPTopAppBar(
    title: String,
    popCallback: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Row(
            modifier = Modifier.padding(sizeMedium),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TopAppBarMenu(
                isTheLastMenu = false,
                onClickCallback = popCallback
            ) {
                Icon(
                    contentDescription = "Notifications",
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
                modifier = Modifier.size(topAppBarMenuSize)
            ) {}
        }
    }
}

@Composable
private fun MyWaifuMPTopAppBar(
    title: String,
    leadingTitle: String?,
    expanded: Boolean,
    expandedCallback: (() -> Unit)?,
    notificationCallback: (() -> Unit)?,
    searchCallback: ((String) -> Unit)?,
    burgerCallback: (() -> Unit)?,
    burgerContent: (@Composable () -> Unit)?
) {
    // --- Container ---
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        // --- --- Container layout --- ---
        Column(
            modifier = Modifier.padding(sizeMedium)
        ) {
            // --- --- --- First row --- --- ---
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // --- --- --- --- Title container --- --- --- ---
                Column(
                    modifier = Modifier
                        .height(topAppBarMenuSize)
                        .weight(3f),
                    verticalArrangement = Arrangement.Center
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
                // --- --- --- --- Menus --- --- --- ---
                if (expandedCallback != null) {
                    TopAppBarMenu(
                        isTheLastMenu = false,
                        onClickCallback = expandedCallback
                    ) {
                        Icon(
                            contentDescription = "Notifications",
                            painter = painterResource(
                                if (expanded) {
                                    Res.drawable.caret_up
                                }
                                else {
                                    Res.drawable.caret_down
                                }
                            )
                        )
                    }
                }
                if (notificationCallback != null) {
                    TopAppBarMenu(
                        isTheLastMenu = false,
                        onClickCallback = notificationCallback
                    ) {
                        Icon(
                            contentDescription = "Notifications",
                            painter = painterResource(Res.drawable.bell)
                        )
                    }
                }
                if (burgerCallback != null) {
                    TopAppBarMenu(
                        onClickCallback = burgerCallback
                    ) {
                        Icon(
                            contentDescription = "Notifications",
                            painter = painterResource(Res.drawable.menu_burger)
                        )
                    }
                }
                // DO NOT simply. Sometimes more is more.
                if (burgerContent != null) {
                    burgerContent.invoke()
                }
            }
            // --- --- --- Second row --- --- ---
            if (searchCallback != null) {
                Row(
                    modifier = Modifier
                        .padding(top = sizeMedium)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(topAppBarMenuSize)
                            .clip(RoundedCornerShape(sizeMedium)),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface
                        ),
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.padding(sizeMedium),
                                contentDescription = "Search",
                                painter = painterResource(Res.drawable.search)
                            )
                        },
                        placeholder = {
                            Text("Search...")
                        },
                        lineLimits = TextFieldLineLimits.SingleLine,
                        state = rememberTextFieldState()
                    )
                }
            }
        }
    }
}

/**
 * Expanded.
 */
@Composable
fun MyWaifuTopAppBar(
    title: String,
    collapseCallback: () -> Unit,
    notificationCallback: () -> Unit,
    burgerCallback: () -> Unit,
    searchCallback: (String) -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPTopAppBar(
        title = title,
        leadingTitle = null,
        expanded = true,
        expandedCallback = collapseCallback,
        notificationCallback = notificationCallback,
        searchCallback = searchCallback,
        burgerCallback = burgerCallback,
        burgerContent = burgerContent
    )
}

/**
 * Expanded with leading title.
 */
@Composable
fun MyWaifuTopAppBar(
    title: String,
    leadingTitle: String,
    collapseCallback: () -> Unit,
    notificationCallback: () -> Unit,
    burgerCallback: () -> Unit,
    searchCallback: (String) -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPTopAppBar(
        title = title,
        leadingTitle = leadingTitle,
        expanded = true,
        expandedCallback = collapseCallback,
        notificationCallback = notificationCallback,
        searchCallback = searchCallback,
        burgerCallback = burgerCallback,
        burgerContent = burgerContent
    )
}

/**
 * Collapsed.
 */
@Composable
fun MyWaifuTopAppBar(
    title: String,
    expandCallback: () -> Unit,
    notificationCallback: () -> Unit,
    burgerCallback: () -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPTopAppBar(
        title = title,
        leadingTitle = null,
        expanded = false,
        expandedCallback = expandCallback,
        notificationCallback = notificationCallback,
        burgerCallback = burgerCallback,
        searchCallback = null,
        burgerContent = burgerContent
    )
}

/**
 * Collapsed with leading title.
 */
@Composable
fun MyWaifuTopAppBar(
    title: String,
    leadingTitle: String,
    expandCallback: () -> Unit,
    notificationCallback: () -> Unit,
    burgerCallback: () -> Unit,
    burgerContent: @Composable () -> Unit
) {
    MyWaifuMPTopAppBar(
        title = title,
        leadingTitle = leadingTitle,
        expanded = false,
        expandedCallback = expandCallback,
        notificationCallback = notificationCallback,
        burgerCallback = burgerCallback,
        searchCallback = null,
        burgerContent = burgerContent
    )
}

@Composable
fun MyWaifuTopAppBar(
    title: String,
    popCallback: () -> Unit
) {
    MyWaifuMPTopAppBar(
        title = title,
        popCallback = popCallback
    )
}

/**
 * Previews
 */
@Preview(
    name = "Expanded"
)
@Composable
fun MyWaifuTopAppBarPreview1() {
    MyWaifuPreview {
        MyWaifuTopAppBar(
            title = "Administrator",
            collapseCallback = {},
            notificationCallback = {},
            searchCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}

@Preview(
    name = "Expanded with leading title"
)
@Composable
fun MyWaifuTopAppBarPreview2() {
    MyWaifuPreview {
        MyWaifuTopAppBar(
            title = "Administrator",
            leadingTitle = "Welcome",
            collapseCallback = {},
            notificationCallback = {},
            searchCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}

@Preview(
    name = "Collapsed"
)
@Composable
fun MyWaifuTopAppBarPreview3() {
    MyWaifuPreview {
        MyWaifuTopAppBar(
            title = "Administrater",
            expandCallback = {},
            notificationCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}

@Preview(
    name = "Collapsed with leading title"
)
@Composable
fun MyWaifuTopAppBarPreview4() {
    MyWaifuPreview {
        MyWaifuTopAppBar(
            title = "Administrater",
            leadingTitle = "Welcome",
            expandCallback = {},
            notificationCallback = {},
            burgerCallback = {},
            burgerContent = {}
        )
    }
}

@Preview(
    name = "Non home version"
)
@Composable
fun MyWaifuTopAppBarPreview5() {
    MyWaifuPreview {
        MyWaifuTopAppBar(
            title = "Non home route",
            popCallback = {}
        )
    }
}
