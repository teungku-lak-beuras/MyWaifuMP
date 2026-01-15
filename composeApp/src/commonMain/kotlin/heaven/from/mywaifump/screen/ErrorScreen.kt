package heaven.from.mywaifump.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.utility.MyWaifuPreview

@Composable
fun ErrorScreen(message: String = "Why error? :(") {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Error! XD"
            )
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() = MyWaifuPreview {
    ErrorScreen()
}
