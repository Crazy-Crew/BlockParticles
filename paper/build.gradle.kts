plugins {
    id("paper-plugin")
}

repositories {
    maven("https://repo.crazycrew.us/snapshots/")

    maven("https://repo.crazycrew.us/releases/")
}

dependencies {
    implementation("com.ryderbelserion.ruby", "ruby-paper", "1.2-snapshot")

    implementation("org.bstats", "bstats-bukkit", "3.0.2")

    //api(project(":common"))
}

tasks {
    shadowJar {
        listOf(
            "com.ryderbelserion.ruby",
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