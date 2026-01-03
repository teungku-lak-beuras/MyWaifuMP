import org.jetbrains.compose.desktop.application.dsl.TargetFormat

val nameSpace = "heaven.from.mywaifump"

plugins {
    alias(libs.plugins.heaven.from.kmp.application)
    alias(libs.plugins.heaven.from.cmp)
    alias(libs.plugins.heaven.from.coil)
    alias(libs.plugins.heaven.from.room)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:buildconfig"))
            implementation(project(":core:model"))
            implementation(project(":core:local"))
            implementation(project(":core:network"))
            implementation(project(":core:repository"))
        }

        commonTest.dependencies {
        }

        androidMain.dependencies {
        }

        jvmMain.dependencies {
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

android {
    namespace = nameSpace

    defaultConfig {
        applicationId = nameSpace
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

compose.desktop {
    application {
        mainClass = "$nameSpace.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = nameSpace
            packageVersion = "1.0.0"
        }
    }
}
