package heaven.from.mywaifump.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import heaven.from.mywaifump.utility.MyWaifuPreview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun LoadingScreenPreview() = MyWaifuPreview {
    LoadingScreen()
}
