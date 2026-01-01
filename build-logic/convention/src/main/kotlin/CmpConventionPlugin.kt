
import heaven.from.buildlogic.configureComposeMultiplatform
import heaven.from.buildlogic.findPluginId
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CmpConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("*** ${this::class.java.simpleName} invoked ***")

        with (target) {
            with (pluginManager) {
                apply(libs.findPluginId("composeMultiplatform"))
                apply(libs.findPluginId("composeCompiler"))
            }

            extensions.configure<KotlinMultiplatformExtension> {
                configureComposeMultiplatform(this)
            }
        }
    }
}
