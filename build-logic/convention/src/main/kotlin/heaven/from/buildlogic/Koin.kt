package heaven.from.buildlogic

import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project

fun Project.configureKoinSafety(
    extension: KspExtension
) {
    extension.apply {
        arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
        arg("KOIN_CONFIG_CHECK", "true")
    }
}
