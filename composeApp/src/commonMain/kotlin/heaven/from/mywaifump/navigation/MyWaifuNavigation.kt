package heaven.from.mywaifump.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import heaven.from.mywaifump.route.AboutRoute
import heaven.from.mywaifump.route.HelpRoute
import heaven.from.mywaifump.route.HomeRoute
import heaven.from.mywaifump.route.SettingsRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

private const val durationMilliseconds = 300
private const val delayMilliseconds = 100
private const val opacityAlpha = 0.4f

@Composable
fun MyWaifuNavigation() {
    val backstack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(
                        MyWaifuRoutes.HomeRoute::class,
                        MyWaifuRoutes.HomeRoute.serializer()
                    )
                    subclass(
                        MyWaifuRoutes.HelpScreen::class,
                        MyWaifuRoutes.HelpScreen.serializer()
                    )
                    subclass(
                        MyWaifuRoutes.SettingsScreen::class,
                        MyWaifuRoutes.SettingsScreen.serializer()
                    )
                    subclass(
                        MyWaifuRoutes.AboutScreen::class,
                        MyWaifuRoutes.AboutScreen.serializer()
                    )
                }
            }
        },
        MyWaifuRoutes.HomeRoute
    )

    NavDisplay(
        backStack = backstack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                initialOffsetX = { it }
            ) togetherWith slideOutHorizontally(
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
        popTransitionSpec = {
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
            ) togetherWith slideOutHorizontally(
                animationSpec = tween(
                    durationMillis = durationMilliseconds + delayMilliseconds,
                ),
                targetOffsetX = { it }
            )
        },
        entryProvider = entryProvider {
            entry<MyWaifuRoutes.HomeRoute> {
                HomeRoute(
                    helpCallback = {
                        backstack.add(MyWaifuRoutes.HelpScreen)
                    },
                    settingsCallback = {
                        backstack.add(MyWaifuRoutes.SettingsScreen)
                    },
                    aboutCallback = {
                        backstack.add(MyWaifuRoutes.AboutScreen)
                    }
                )
            }
            entry<MyWaifuRoutes.HelpScreen> {
                HelpRoute(
                    popCallback = {
                        backstack.remove(MyWaifuRoutes.HelpScreen)
                    }
                )
            }
            entry<MyWaifuRoutes.SettingsScreen> {
                SettingsRoute(
                    popCallback = {
                        backstack.remove(MyWaifuRoutes.SettingsScreen)
                    }
                )
            }
            entry<MyWaifuRoutes.AboutScreen> {
                AboutRoute(
                    popCallback = {
                        backstack.remove(MyWaifuRoutes.AboutScreen)
                    }
                )
            }
        }
    )
}
