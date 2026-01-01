import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "heaven.from.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.android.gradlePlugin)
    implementation(libs.ksp)
    implementation(libs.room.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("AndroidLibraryConventionPlugin") {
            id = "heaven.from.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("KmpApplicationConventionPlugin") {
            id = "heaven.from.kmp.application"
            implementationClass = "KmpApplicationConventionPlugin"
        }
        register("KmpLibraryConventionPlugin") {
            id = "heaven.from.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("CmpConventionPlugin") {
            id = "heaven.from.cmp"
            implementationClass = "CmpConventionPlugin"
        }
        register("CoilConventionPlugin") {
            id = "heaven.from.coil"
            implementationClass = "CoilConventionPlugin"
        }
        register("KtorConventionPlugin") {
            id = "heaven.from.ktor"
            implementationClass = "KtorConventionPlugin"
        }
        register("RoomConventionPlugin") {
            id = "heaven.from.room"
            implementationClass = "RoomConventionPlugin"
        }
    }
}
