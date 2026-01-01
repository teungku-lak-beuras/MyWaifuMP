
import heaven.from.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class CoilConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("*** ${this::class.java.simpleName} invoked ***")

        with (target) {
            extensions.configure<KotlinMultiplatformExtension> {
                sourceSets.apply {
                    commonMain.dependencies {
                        implementation(libs.findLibrary("coil.compose").get())
                        implementation(libs.findLibrary("coil.network.ktor").get())
                        implementation(libs.findLibrary("ktor.client.cio").get())
                    }
                }
            }
        }
    }
}
