plugins {
    id("paper-plugin")
}

repositories {
    maven("https://repo.crazycrew.us/releases/")
}

dependencies {
    implementation("net.kyori", "adventure-platform-bukkit", "4.3.0")

    implementation("org.bstats", "bstats-bukkit", "3.0.2")

    implementation(project(":common"))
}

tasks {
    shadowJar {
        listOf(
            "org.bstats"
        ).forEach {
            relocate(it, "libs.$it")
        }
    }

    processResources {
        val props = mapOf(
            "name" to rootProject.name,
            "group" to rootProject.group,
            "version" to rootProject.version,
            "description" to rootProject.description,
            "authors" to rootProject.properties["authors"],
            "apiVersion" to "1.20",
            "website" to "https://modrinth.com/plugin/${rootProject.name.lowercase()}"
        )

        filesMatching("plugin.yml") {
             expand(props)
        }
    }
}