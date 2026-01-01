
import com.android.build.api.dsl.LibraryExtension
import com.google.devtools.ksp.gradle.KspExtension
import heaven.from.buildlogic.configureKoinSafety
import heaven.from.buildlogic.configureKotlinAndroid
import heaven.from.buildlogic.configureKotlinMultiplatform
import heaven.from.buildlogic.findPluginId
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("*** ${this::class.java.simpleName} invoked ***")

        with (target) {
            with (pluginManager) {
                apply(libs.findPluginId("kotlinMultiplatform"))
                apply(libs.findPluginId("kotlinSerialization"))
                apply(libs.findPluginId("ksp"))
                apply(libs.findPluginId("androidLibrary"))
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureKotlinMultiplatform(this)
            }

            extensions.configure<KspExtension> {
                configureKoinSafety(this)
            }
        }
    }
}
