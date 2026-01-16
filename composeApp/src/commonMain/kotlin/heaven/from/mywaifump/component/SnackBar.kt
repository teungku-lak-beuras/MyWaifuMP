package heaven.from.mywaifump.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.constant.sizeSmall
import heaven.from.mywaifump.constant.sizeSmaller
import heaven.from.mywaifump.constant.topAppBarMenuSize

/**
 * @param isIndefinitelyDuration either null for short, false for long, and true for indefinitely.
 */
@Composable
fun MyWaifuSnackBar(
    alignment: Alignment = Alignment.BottomCenter,
    isIndefinitelyDuration: Boolean? = null,
    message: String
) {
    val snackbarHostState = remember  { SnackbarHostState() }
    var duration: SnackbarDuration

    when (isIndefinitelyDuration) {
        null -> duration = SnackbarDuration.Short
        false -> duration = SnackbarDuration.Long
        true -> duration = SnackbarDuration.Indefinite
    }

    LaunchedEffect(Unit) {
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration
        )
    }

    SnackbarHost(hostState = snackbarHostState) {
        Box(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.ime)
                .fillMaxSize()
        ) {
            val shape = RoundedCornerShape(sizeMedium)

            Surface(
                modifier = Modifier
                    .padding(sizeMedium)
                    .height(topAppBarMenuSize)
                    .fillMaxWidth()
                    .align(alignment)
                    .dropShadow(
                        shape = shape,
                        shadow = Shadow(
                            alpha = 0.4f,
                            radius = sizeSmaller,
                            color = MaterialTheme.colorScheme.inverseSurface,
                            offset = DpOffset(0.dp, 2.dp)
                        )
                    )
                    .clip(shape),
                color = MaterialTheme.colorScheme.inverseSurface
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = sizeMedium,
                            vertical = sizeSmall
                        ),
                        text = message
                    )
                }
            }
        }
    }
}
