package heaven.from.mywaifump.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.layout.MyWaifuScaffold

@Composable
fun HelpScreen(
    popCallback: () -> Unit
) {
    MyWaifuScaffold(
        topAppBar = {
            MyWaifuTopAppBar(
                title = "Help",
                popCallback = popCallback
            )
        }
    ) { paddingValues ->
        Text(
            modifier = Modifier.padding(paddingValues),
            text = "Help screen is under construction :)"
        )
    }
}
