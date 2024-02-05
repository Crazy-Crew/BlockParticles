plugins {
    id("paper-plugin")
}

val mcVersion = rootProject.properties["minecraftVersion"] as String

dependencies {
    implementation(libs.metrics)

    compileOnly(fileTree("libs").include("*.jar"))
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
        val properties = hashMapOf(
            "name" to rootProject.name,
            "version" to project.version,
            "group" to rootProject.group,
            "description" to rootProject.description,
            "apiVersion" to rootProject.properties["apiVersion"],
            "authors" to rootProject.properties["authors"],
            "website" to rootProject.properties["website"]
        )

        inputs.properties(properties)

        filesMatching("plugin.yml") {
            expand(properties)
        }
    }
}