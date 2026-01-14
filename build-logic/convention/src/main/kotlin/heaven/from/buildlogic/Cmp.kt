package heaven.from.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun Project.configureComposeMultiplatform(
    extension: KotlinMultiplatformExtension
) {
    extension.apply {
        val composeDependencies = extensions.getByType<ComposePlugin.Dependencies>()

        sourceSets.apply {
            commonMain.dependencies {
                implementation(composeDependencies.runtime)
                implementation(composeDependencies.foundation)
                implementation(composeDependencies.material3)
                implementation(composeDependencies.ui)
                implementation(composeDependencies.components.resources)
                implementation(composeDependencies.components.uiToolingPreview)

                // --- View model ---
                implementation(libs.findLibrary("androidx.lifecycle.runtime").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodelCompose").get())

                // --- Navigation 3 with KotlinX JSON serialization ---
                implementation(libs.findLibrary("androidx.navigation3.ui").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodelNavigation3").get())
                implementation(libs.findLibrary("kotlinSerializationJson").get())

                // --- Windiw size class ---
                implementation(libs.findLibrary("compose.material.windowsize").get())
            }

            androidMain.dependencies {
                implementation(composeDependencies.preview)
                implementation(libs.findLibrary("androidx.activity.compose").get())
            }

            jvmMain.dependencies {
                implementation(composeDependencies.desktop.currentOs)
                implementation(libs.findLibrary("kotlinx.coroutinesSwing").get())
            }
        }
    }
}
