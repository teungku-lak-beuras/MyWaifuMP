package heaven.from.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * libs everywhere.
 */
val Project.libs get(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

/**
 * Find plugin id directly.
 */
internal fun VersionCatalog.findPluginId(alias: String): String {
    return findPlugin(alias).get().get().pluginId
}
