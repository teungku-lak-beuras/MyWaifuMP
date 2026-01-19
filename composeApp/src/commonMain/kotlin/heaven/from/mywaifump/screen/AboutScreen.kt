package heaven.from.mywaifump.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import heaven.from.mywaifump.component.MyWaifuTopAppBar
import heaven.from.mywaifump.constant.sizeMedium
import heaven.from.mywaifump.layout.MyWaifuScaffold
import heaven.from.mywaifump.utility.MyWaifuPreview
import mywaifump.composeapp.generated.resources.Res
import mywaifump.composeapp.generated.resources.nekos_best_api
import mywaifump.composeapp.generated.resources.nekosia_cat_api
import org.jetbrains.compose.resources.painterResource

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
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(
                        top = sizeMedium,
                        start = sizeMedium,
                        end = sizeMedium
                    ),
                style = MaterialTheme.typography.titleLarge,
                text = "Hi, there! :)"
            )
            Column(
                modifier = Modifier.padding(sizeMedium)
            ) {
                Text(
                    textAlign = TextAlign.Justify,
                    text = "This application is MyWaifuMP (MP for Multi-Platform). The aim of " +
                        " this project is to hunt for waifus, from any platform. :)" +
                        "\n\n" +
                        "The application is getting more stable each commit and one day, I hope " +
                        "that this project will help someone out there learning Compose and/or " +
                        "Kotlin Multi-Platform. But, since I don't have Apple products (iPhone " +
                        "and Macintosh, and I'll likely never have one), I decided to exclude " +
                        "those platforms from this project's target." +
                        "\n\n" +
                        "I also learnt a lot when implementing this project. So, one day, when I " +
                        "decide to move-on to other more interesting project, I'll have a very " +
                        "solid understanding. I will never archive this project at my GitHub " +
                        "repository. This project is my pride and my first experience. The " +
                        "previous project (the Android version) is an exception because I " +
                        "migrated all of the code bases from there to this project." +
                        "\n\n" +
                        "This project is open-source. You can see the source code and modify it " +
                        "by yourself! :)"
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Nekos Best API"
                )
                Image(
                    modifier = Modifier.padding(top = sizeMedium),
                    contentDescription = "Nekos Best API website image",
                    painter = painterResource(Res.drawable.nekos_best_api)
                )
                Text(
                    modifier = Modifier.padding(top = sizeMedium),
                    textAlign = TextAlign.Justify,
                    text = "This API is the first API used for this project, and the project " +
                    "before (the Android version). This API is very good, never down (unless the " +
                    "Cloudflare is down), response time is very low, no limitation, lots, lots " +
                    "of beautiful waifus, and very consistent." +
                    "\n\n" +
                    "The only limitation of this API is that, there's currently no support for " +
                    "compressed images. This makes loading images in the home screen take a " +
                    "really long time. Anything else? Nothing. This API is the best so far. I " +
                    "love it."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "Nekosia Cat API"
                )
                Image(
                    modifier = Modifier.padding(top = sizeMedium),
                    contentDescription = "Nekos Best API website image",
                    painter = painterResource(Res.drawable.nekosia_cat_api)
                )
                Text(
                    modifier = Modifier.padding(top = sizeMedium),
                    textAlign = TextAlign.Justify,
                    text = "This API is the second API which I considered using. It has almost " +
                    "all of the benefits from Nekos Best API (above) plus support for compressed " +
                    "images." +
                    "\n\n" +
                    "The only limitation of this API is that there are limitations for API and " +
                    "image CDN requests. This is perfectly valid nothing to blame. But, this " +
                    "makes me a little nervous when scrolling while using the app, because based " +
                    "current limitation at the writing of this text is that, maximum request " +
                    "allowed is around 1 per second for the API and around 2-5 per second for " +
                    "image CDN request." +
                    "\n\n" +
                    "Another thing is that most of the waifus are lollis. Looking at lolli anime " +
                    "girl while searching for a waifu makes me a bit nervous. But, this is " +
                    "subjective and has nothing to do with the API itself. The owner(s)" +
                    "deserve(s) full rights for his/her/their API." +
                    "\n\n" +
                    "Overall, this API is the best. Super low latency, and gives me a very " +
                    "familiar experiences with Nekos Best API (above)."
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = sizeMedium))
                Text(
                    textAlign = TextAlign.Justify,
                    text = "I don't accept donations, and I will never accept those. If you want " +
                    "to help me, hire me instead. Other than that, I thank you very much for " +
                    "your good intention. :)"
                )
            }
        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() = MyWaifuPreview {
    AboutScreen(
        popCallback = {}
    )
}
