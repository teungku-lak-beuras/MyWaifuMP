package heaven.from.mywaifump.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.layout.MyWaifuScaffold

@Composable
fun AboutScreen(
    popCallback: () -> Unit
) {
    MyWaifuScaffold(
        topAppBar = {
            MyWaifuTopAppBar(
                title = "About",
                popCallback = popCallback
            )
        }
    ) { paddingValues ->
        Text(
            modifier = Modifier.padding(paddingValues),
            text = "About screen is under construction. :)"
        )
    }
}
