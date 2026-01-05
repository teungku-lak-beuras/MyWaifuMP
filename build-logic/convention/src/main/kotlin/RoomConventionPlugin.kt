import androidx.room.gradle.RoomExtension
import heaven.from.buildlogic.findPluginId
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with (target) {
            with (pluginManager) {
                apply(libs.findPluginId("androidx.room"))
            }

            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    androidMain.dependencies {
                        implementation(libs.findLibrary("androidx.room.runtime").get())
                        implementation(libs.findLibrary("androidx.sqlite.bundled").get())
                        implementation(libs.findLibrary("androidx.room.sqlite.wrapper").get())
                    }

                    jvmMain.dependencies {
                        implementation(libs.findLibrary("androidx.room.runtime").get())
                        implementation(libs.findLibrary("androidx.sqlite.bundled").get())
                    }

                    wasmJsMain.dependencies {
//                        implementation(libs.findLibrary("indexeddb-core").get())
                    }
                }
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies.apply {
                add("kspAndroid", libs.findLibrary("androidx.room.compiler").get())
                add("kspJvm", libs.findLibrary("androidx.room.compiler").get())
            }
        }
    }
}
