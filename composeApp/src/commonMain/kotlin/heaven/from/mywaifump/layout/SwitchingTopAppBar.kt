package heaven.from.mywaifump.layout

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

@Composable
fun MyWaifuSwitchingTopAppBar(
    expanded: Boolean,
    expandedTopAppBar: @Composable () -> Unit,
    collapsedTopAppBar: @Composable () -> Unit,
) {
    AnimatedContent(
        transitionSpec = {
            fadeIn() + expandVertically() togetherWith fadeOut() + shrinkVertically()
        },
        targetState = expanded
    ) { targetState ->
        when (targetState) {
            true -> expandedTopAppBar.invoke()
            false -> collapsedTopAppBar.invoke()
        }
    }
}
