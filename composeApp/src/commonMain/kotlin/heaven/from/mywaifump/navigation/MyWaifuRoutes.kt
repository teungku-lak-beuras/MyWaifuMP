package heaven.from.mywaifump.navigation

import androidx.navigation3.runtime.NavKey
import heaven.from.model.MyWaifuModelV2
import kotlinx.serialization.Serializable

@Serializable
sealed interface MyWaifuRoutes : NavKey {
    @Serializable
    data object HomeRoute : MyWaifuRoutes, NavKey

    @Serializable
    data class DetailRoute(val waifu: MyWaifuModelV2) : MyWaifuRoutes, NavKey

    @Serializable
    data object HelpRoute : MyWaifuRoutes, NavKey

    @Serializable
    data object SettingsRoute : MyWaifuRoutes, NavKey

    @Serializable
    data object AboutRoute : MyWaifuRoutes, NavKey
}
