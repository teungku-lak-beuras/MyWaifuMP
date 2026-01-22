package heaven.from.mywaifump.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.constant.sizeAppBarMenu
import heaven.from.mywaifump.constant.sizeSmall
import heaven.from.mywaifump.constant.sizeSmaller

@Composable
fun MyWaifuAppBarMenu(
    modifier: Modifier = Modifier,
    isTheLastMenu: Boolean = true,
    onClickCallback: () -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .let {
                if (!isTheLastMenu) {
                    it.padding(end = sizeSmall)
                }
                else {
                    it
                }
            }
            .size(sizeAppBarMenu)
            .clip(RoundedCornerShape(sizeMedium))
            .clickable(onClick = onClickCallback),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(
            modifier = Modifier.padding(sizeSmaller + sizeSmall),
            contentAlignment = Alignment.Center
        ) {
            content.invoke()
        }
    }
}
