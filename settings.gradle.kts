pluginManagement {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")

        gradlePluginPortal()
    }
}

rootProject.name = "BlockParticles"

listOf(
    "paper"
).forEach {
    include(it)
}