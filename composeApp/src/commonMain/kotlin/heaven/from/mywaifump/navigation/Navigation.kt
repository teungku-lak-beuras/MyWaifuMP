package heaven.from.mywaifump.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import heaven.from.mywaifump.route.HomeRoute

private const val durationMilliseconds = 300
private const val delayMilliseconds = 100
private const val opacityAlpha = 0.4f

@Composable
fun MyWaifuNavigation() {
    NavHost(
        navController = rememberNavController(),
        enterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                initialOffsetX = { it }
            )
        },
        exitTransition = {
            slideOutHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds,
                    delayMillis = delayMilliseconds
                ),
                targetOffsetX = { -it / 3 }
            ) + fadeOut(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                targetAlpha = opacityAlpha
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds,
                    delayMillis = delayMilliseconds
                ),
                initialOffsetX = { -it / 3 }
            ) + fadeIn(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                initialAlpha = opacityAlpha
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                targetOffsetX = { it }
            )
        },
        startDestination = Routes.Home().name
    ) {
        composable(Routes.Home().name) {
            HomeRoute(
                helpCallback = {},
                settingsCallback = {},
                aboutCallback = {}
            )
        }
    }
}
