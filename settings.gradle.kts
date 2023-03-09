@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    includeBuild("build-src")

    versionCatalogs {
        create("settings") {
            from(files("gradle/settings.versions.toml"))
        }
    }

    repositories.gradlePluginPortal()
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "CrazyParticles"

val lowerCase = rootProject.name.lowercase()

include("api")
project(":api").name = "$lowerCase-api"

listOf("platforms").forEach(::includeProject)

listOf("spigot", "v2").forEach(::includePlatform)

fun includeProject(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
    }
}

fun includeModule(name: String) {
    include(name) {
        this.name = "$lowerCase-module-$name"
        this.projectDir = file("modules/$name")
    }
}

fun includePlatform(name: String) {
    include(name) {
        this.name = "$lowerCase-$name"
        this.projectDir = file("platforms/$name")
    }
}

fun includePlatformModule(name: String, platform: String) {
    include(name) {
        this.name = "$lowerCase-module-$platform-$name"
        this.projectDir = file("modules/$platform/$name")
    }
}

fun include(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}