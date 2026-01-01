package heaven.from.mywaifump.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

@Composable
fun MyWaifuSwitchingTopAppBar(
    expanded: Boolean,
    expandedTopAppBar: @Composable () -> Unit,
    collapsedTopAppBar: @Composable () -> Unit,
) {
    Row {
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            expandedTopAppBar.invoke()
        }
        AnimatedVisibility(
            visible = !expanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            collapsedTopAppBar.invoke()
        }
    }
}
