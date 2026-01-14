package heaven.from.mywaifump.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MyWaifuRoutes : NavKey {
    @Serializable
    data object HomeRoute : MyWaifuRoutes, NavKey

    @Serializable
    data object HelpScreen : MyWaifuRoutes, NavKey

    @Serializable
    data object SettingsScreen : MyWaifuRoutes, NavKey

    @Serializable
    data object AboutScreen : MyWaifuRoutes, NavKey
}
