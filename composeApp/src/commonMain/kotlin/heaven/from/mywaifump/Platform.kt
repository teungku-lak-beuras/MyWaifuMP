package heaven.from.mywaifump

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
