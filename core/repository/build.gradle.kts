val nameSpace = "heaven.from.repository"

plugins {
    alias(libs.plugins.heaven.from.kmp.library)
    alias(libs.plugins.heaven.from.ktor)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
            api(project(":core:network"))
        }
    }
}

android {
    namespace = nameSpace
}
