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
                implementation(libs.findLibrary("compose.components.resources").get())
                implementation(libs.findLibrary("compose.foundation").get())
                implementation(libs.findLibrary("compose.material3").get())
                implementation(libs.findLibrary("compose.ui").get())
                implementation(libs.findLibrary("compose.ui.tooling.preview").get())
                implementation(libs.findLibrary("compose.runtime").get())

                // --- View model ---
                implementation(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())

                // --- Navigation 3 with KotlinX JSON serialization ---
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel.navigation3").get())
                implementation(libs.findLibrary("androidx.navigation3.ui").get())
                implementation(libs.findLibrary("kotlin.serialization.json").get())

                // --- Window size class ---
                implementation(libs.findLibrary("compose.material.windowsize").get())
            }

            commonTest.dependencies {
                implementation(libs.findLibrary("kotlin.test").get())
            }

            androidMain.dependencies {
                implementation(libs.findLibrary("androidx.activity.compose").get())
            }

            jvmMain.dependencies {
//                implementation(libs.findLibrary("compose.desktop").get()
                implementation(composeDependencies.desktop.currentOs)
                implementation(libs.findLibrary("kotlinx.coroutines.swing").get())
            }
        }
    }
}
