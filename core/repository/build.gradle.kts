val nameSpace = "heaven.from.repository"

plugins {
    alias(libs.plugins.heaven.from.kmp.library)
    alias(libs.plugins.heaven.from.ktor)
    alias(libs.plugins.heaven.from.room)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
            implementation(project(":core:local"))
            implementation(project(":core:network"))
        }
    }
}

android {
    namespace = nameSpace
}
