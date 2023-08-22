plugins {
    id("xyz.jpenilla.run-paper") version "2.1.0"

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

    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("1.20.1")

        jvmArgs("-Dnet.kyori.ansi.colorLevel=truecolor")
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