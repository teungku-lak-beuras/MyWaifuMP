package heaven.from.buildlogic

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object JavaSdk {
    val JvmVersion = JavaVersion.VERSION_17
    val KotlinJvmVersion = JvmTarget.JVM_17
    const val KotlinJvmToolchainVersion = 17
}
