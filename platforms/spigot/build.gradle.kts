@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazyparticles.spigot-plugin")
}

repositories {
    /**
     * NBT Team
     */
    maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    compileOnly(libs.spigotmc)

    implementation(libs.nbt.api)

    implementation(libs.bstats.bukkit) {
        exclude("org.bukkit", "bukkit")
    }
}

val projectDescription = settings.versions.projectDescription.get()
val projectGithub = settings.versions.projectGithub.get()
val projectGroup = settings.versions.projectGroup.get()
val projectName = settings.versions.projectName.get()
val projectExt = settings.versions.projectExtension.get()

val isBeta = settings.versions.projectBeta.get().toBoolean()

val projectVersion = "1.11.1"

val finalVersion = if (isBeta) "$projectVersion+beta" else projectVersion

tasks {
    shadowJar {
        archiveFileName.set("${projectName}+${projectDir.name}+$finalVersion.jar")

        listOf(
            "de.tr7zw.changeme.nbtapi",
            "org.bstats"
        ).forEach { relocate(it, "$projectGroup.library.$it") }
    }

    processResources {
        filesMatching("plugin.yml") {
             expand(
                "name" to projectName,
                "group" to projectGroup,
                "version" to finalVersion,
                "description" to projectDescription,
                "website" to "https://modrinth.com/$projectExt/${projectName.lowercase()}"
            )
        }
    }
}