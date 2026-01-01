val nameSpace = "heaven.from.network"

plugins {
    alias(libs.plugins.heaven.from.kmp.library)
    alias(libs.plugins.heaven.from.ktor)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:buildconfig"))
            implementation(project(":core:model"))
        }
    }
}

android {
    namespace = nameSpace
}
