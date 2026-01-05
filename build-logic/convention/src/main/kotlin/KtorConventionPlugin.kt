
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KtorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.core").get())
                        implementation(libs.findLibrary("ktor.client.logging").get())
                        implementation(libs.findLibrary("ktor.client.content.negotiation").get())
                        implementation(libs.findLibrary("ktor.serialization.kotlinx.json").get())
                    }

                    androidMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.cio").get())
                    }

                    jvmMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.cio").get())
                    }

                    wasmJsMain.dependencies {
                        implementation(libs.findLibrary("ktor.client.js").get())
                    }
                }
            }
        }
    }
}
