pluginManagement {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")

        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        maven("https://repo.papermc.io/repository/maven-public/")

        gradlePluginPortal()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("adventure4", "4.14.0")
            version("cluster", "6.3")
            version("cloud", "1.8.4")

            library("triumphgui", "dev.triumphteam", "triumph-gui").version("3.1.7")

            library("cluster_paper", "com.ryderbelserion.cluster", "paper").versionRef("cluster")
            library("cluster_api", "com.ryderbelserion.cluster", "paper").versionRef("cluster")

            library("cloud_core", "cloud.commandframework", "cloud-core").versionRef("cloud")
            library("cloud_brig", "cloud.commandframework", "cloud-brigadier").versionRef("cloud")
            library("cloud_paper", "cloud.commandframework", "cloud-paper").versionRef("cloud")
            library("cloud_extras", "cloud.commandframework", "cloud-minecraft-extras").versionRef("cloud")

            library("adventure4", "net.kyori", "adventure-text-minimessage").versionRef("adventure4")
            library("minimessage4", "net.kyori", "adventure-api").versionRef("adventure4")

            library("itemsadder", "com.github.LoneDev6", "api-itemsadder").version("3.6.1")

            library("placeholderapi", "me.clip", "placeholderapi").version("2.11.5")
            library("metrics", "org.bstats", "bstats-bukkit").version("3.0.2")
            library("oraxen", "io.th0rgal", "oraxen").version("1.164.0")
            library("configme", "ch.jalu", "configme").version("1.4.1")

            bundle("adventure", listOf("adventure4", "minimessage4"))
        }
    }
}

rootProject.name = "BlockParticles"

include("api")
include("paper")
//include("fabric")
include("common")