package heaven.from.mywaifump.navigation

private interface Route {
    val name: String
}

sealed class Routes : Route {
    class Home(override val name: String = "Home") : Route
    class Help(override val name: String = "Help") : Route
    class Settings(override val name: String = "Settings") : Route
    class About(override val name: String = "About") : Route
}
