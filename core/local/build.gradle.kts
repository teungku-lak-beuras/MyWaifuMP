val nameSpace = "heaven.from.local"

plugins {
    alias(libs.plugins.heaven.from.kmp.library)
    alias(libs.plugins.heaven.from.room)
}

android {
    namespace = nameSpace
}
