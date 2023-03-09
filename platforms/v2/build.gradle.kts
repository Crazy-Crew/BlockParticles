@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("crazyparticles.paper-plugin")

    alias(settings.plugins.minotaur)
}

repositories {
    /**
     * NBT Team
     */
    maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
    api(project(":crazyparticles-api"))

    compileOnly(libs.papermc)

    compileOnly(libs.crazycore.paper)

//    implementation(libs.triumph.cmds)
//    implementation(libs.triumph.gui)

    compileOnly(libs.config.me)

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

val projectVersion = settings.versions.projectVersion.get()

val finalVersion = if (isBeta) "$projectVersion+beta" else projectVersion

val type = if (isBeta) "beta" else "release"

tasks {
    shadowJar {
        archiveFileName.set("${projectName}+${projectDir.name}+$finalVersion.jar")

        listOf(
            "de.tr7zw.changeme.nbtapi",
            "org.bstats"
        ).forEach { relocate(it, "$projectGroup.library.$it") }
    }

    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN"))
        projectId.set(projectName.lowercase())

        versionName.set("$projectName $finalVersion")
        versionNumber.set(finalVersion)

        versionType.set(type)

        //uploadFile.set(shadowJar.get())

        autoAddDependsOn.set(true)

        gameVersions.addAll(
            listOf(
                "1.19",
                "1.19.1",
                "1.19.2",
                "1.19.3"
            )
        )

        loaders.addAll(listOf("paper", "purpur"))

        //<h3>The first release for CrazyParticles on Modrinth! 🎉🎉🎉🎉🎉<h3><br> If we want a header.
        changelog.set(
            """
                <h3>The first release for CrazyParticles on Modrinth 🎉🎉🎉🎉🎉<h3><br>
                <h4>Changes:</h4>
                 <p>N/A</p>
                <h4>Under the hood changes</h4>
                 <p>N/A</p>
                <h4>Bug Fixes:</h4>
                 <p>N/A</p>
            """.trimIndent()
        )
    }

    processResources {
        filesMatching("paper-plugin.yml") {
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

publishing {
    repositories {
        val repo = if (isBeta) "beta" else "releases"
        maven("https://repo.crazycrew.us/$repo") {
            name = "crazycrew"
            // Used for locally publishing.
            // credentials(PasswordCredentials::class)

            credentials {
                username = System.getenv("REPOSITORY_USERNAME")
                password = System.getenv("REPOSITORY_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = projectGroup
            artifactId = "${projectName.lowercase()}-api"
            version = finalVersion

            from(components["java"])
        }
    }
}