val nameSpace = "heaven.from.buildconfig"

plugins {
    alias(libs.plugins.heaven.from.kmp.library)
}

android {
    namespace = nameSpace
}
