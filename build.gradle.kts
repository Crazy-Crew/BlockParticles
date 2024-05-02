plugins {
    id("io.papermc.hangar-publish-plugin") version "0.1.2"
    id("com.modrinth.minotaur") version "2.+"

    id("io.github.goooler.shadow")

    `root-plugin`
}

val buildNumber: String = System.getenv("NEXT_BUILD_NUMBER") ?: "SNAPSHOT"

rootProject.version = "1.20.6-$buildNumber"

subprojects.filter { it.name != "api" }.forEach {
    it.project.version = rootProject.version
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))

    projectId.set(rootProject.name.lowercase())

    versionType.set("beta")

    versionName.set("${rootProject.name} ${rootProject.version}")
    versionNumber.set(rootProject.version as String)

    changelog.set(System.getenv("COMMIT_MESSAGE"))

    uploadFile.set(rootProject.projectDir.resolve("jars/${rootProject.name}-${rootProject.version}.jar"))

    gameVersions.set(listOf(
        "1.20.6"
    ))

    loaders.add("paper")
    loaders.add("purpur")
    loaders.add("folia")

    autoAddDependsOn.set(false)
    detectLoaders.set(false)
}

hangarPublish {
    publications.register("plugin") {
        apiKey.set(System.getenv("HANGAR_KEY"))

        id.set(rootProject.name.lowercase())

        version.set(rootProject.version as String)

        channel.set("Snapshot")

        changelog.set(System.getenv("COMMIT_MESSAGE"))

        platforms {
            paper {
                jar.set(rootProject.projectDir.resolve("jars/${rootProject.name}-${rootProject.version}.jar"))

                platformVersions.set(listOf(
                    "1.20.6"
                ))
            }
        }
    }
}