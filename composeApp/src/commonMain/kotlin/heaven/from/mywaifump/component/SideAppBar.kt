package heaven.from.mywaifump.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import heaven.from.mywaifump.constant.sizeAppBarMenu
import heaven.from.mywaifump.constant.sizeLarge
import heaven.from.mywaifump.constant.sizeLarger
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.app_name
import mywaifump.composeapp.generated.resources.arrow_small_left
import mywaifump.composeapp.generated.resources.back
import mywaifump.composeapp.generated.resources.bell
import mywaifump.composeapp.generated.resources.menu_burger
import mywaifump.composeapp.generated.resources.notification
import mywaifump.composeapp.generated.resources.search
import mywaifump.composeapp.generated.resources.waifu_icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

private val shape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = sizeLarger,
    bottomStart = 0.dp,
    bottomEnd = sizeLarger
)

@Composable
private fun MyWaifuMPSideAppBar(
    popCallback: (() -> Unit)?,
    burgerCallback: (() -> Unit)?,
    notificationCallback: (() -> Unit)?,
    searchCallback: (() -> Unit)?,
    burgerContent: (@Composable () -> Unit)?
) {
    Surface(
        modifier = Modifier.clip(shape),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = sizeLarge,
                    horizontal = sizeMedium
                )
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                if (popCallback != null) {
                    MyWaifuAppBarMenu(
                        onClickCallback = popCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.back),
                            painter = painterResource(Res.drawable.arrow_small_left)
                        )
                    }
                }
                if (burgerCallback != null) {
                    MyWaifuAppBarMenu(
                        onClickCallback = burgerCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.notification),
                            painter = painterResource(Res.drawable.menu_burger)
                        )
                    }
                }
                if (burgerContent != null) {
                    burgerContent.invoke()
                }
                if (notificationCallback != null) {
                    MyWaifuAppBarMenu(
                        modifier = Modifier.padding(top = sizeMedium),
                        onClickCallback = notificationCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.notification),
                            painter = painterResource(Res.drawable.bell)
                        )
                    }
                }
                if (searchCallback != null) {
                    MyWaifuAppBarMenu(
                        modifier = Modifier.padding(top = sizeMedium),
                        onClickCallback = searchCallback
                    ) {
                        Icon(
                            contentDescription = stringResource(Res.string.search),
                            painter = painterResource(Res.drawable.search)
                        )
                    }
                }
            }
            Image(
                modifier = Modifier
                    .size(sizeAppBarMenu)
                    .clip(RoundedCornerShape(sizeMedium))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        shape = RoundedCornerShape(sizeMedium)
                    ),
                contentDescription = stringResource(Res.string.app_name),
                painter = painterResource(Res.drawable.waifu_icon)
            )
        }
    }
}

@Composable
fun MyWaifuSideAppBar(
    burgerCallback: (() -> Unit),
    notificationCallback: (() -> Unit),
    searchCallback: (() -> Unit),
    burgerContent: (@Composable () -> Unit)
) {
    MyWaifuMPSideAppBar(
        popCallback = null,
        burgerCallback = burgerCallback,
        notificationCallback = notificationCallback,
        searchCallback = searchCallback,
        burgerContent = burgerContent
    )
}

@Composable
fun MyWaifuSideAppBar(
    popCallback: () -> Unit,
    notificationCallback: () -> Unit,
) {
    MyWaifuMPSideAppBar(
        popCallback = popCallback,
        burgerCallback = null,
        notificationCallback = notificationCallback,
        searchCallback = null,
        burgerContent = null
    )
}

@Preview
@Composable
private fun MyWaifuSideAppBarPreview1() = MyWaifuPreview {
    MyWaifuSideAppBar(
        burgerCallback = {},
        notificationCallback = {},
        searchCallback = {},
        burgerContent = {}
    )
}

@Preview
@Composable
private fun MyWaifuSideAppBarPreview2() = MyWaifuPreview {
    MyWaifuSideAppBar(
        popCallback = {},
        notificationCallback = {}
    )
}
