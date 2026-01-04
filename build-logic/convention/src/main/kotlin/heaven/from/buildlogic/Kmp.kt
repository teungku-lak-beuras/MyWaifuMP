package heaven.from.buildlogic

import heaven.from.buildlogic.JavaSdk.KotlinJvmToolchainVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

fun Project.configureKotlinMultiplatform(kmpExtension: KotlinMultiplatformExtension) {
    kmpExtension.apply {
        jvmToolchain(KotlinJvmToolchainVersion)

        androidTarget()

        /* iOS is not supported. I don't have one, and I'll never. Open to contributor though.
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = "ComposeApp"
                isStatic = true
            }
        }
        */

        jvm()

//        js(IR) {
//            browser {
//                binaries.executable()
//            }
//        }

        wasmJs {
            browser()
            binaries.executable()
        }

        sourceSets.apply {
            commonMain.configure {
                kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            }

            commonMain.dependencies {
                implementation(libs.findLibrary("napier").get())
            }

            commonTest.dependencies {
            }

            androidMain.dependencies {
            }
        }
    }

    // Tell the KSP to use "kspCommonMainMetadata" on each platform.
    project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
        if (name != "kspCommonMainKotlinMetadata") {
            dependsOn("kspCommonMainKotlinMetadata")
        }
    }

    // Needed for the project itself to get the Koin compiler working, will not be included
    // within the resulted code.
    dependencies {
    }
}
