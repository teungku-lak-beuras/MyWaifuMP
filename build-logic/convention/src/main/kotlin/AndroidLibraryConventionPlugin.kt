
import com.android.build.api.dsl.LibraryExtension
import heaven.from.buildlogic.configureKotlinAndroid
import heaven.from.buildlogic.findPluginId
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        println("*** ${this::class.java.simpleName} invoked ***")

        with (target) {
            with (pluginManager) {
                apply(libs.findPluginId("androidLibrary"))
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}
