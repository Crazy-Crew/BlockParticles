plugins {
    alias(libs.plugins.shadowJar)
    alias(libs.plugins.runPaper)

    `paper-plugin`
}

dependencies {
    // org.yaml is already bundled with Paper
    implementation(libs.vital.paper) {
        exclude("org.yaml")
    }

    compileOnly(libs.headdatabaseapi)
    api(projects.blockparticlesCore)
}

tasks {
    runServer {
        jvmArgs("-Dnet.kyori.ansi.colorLevel=truecolor")

        defaultCharacterEncoding = Charsets.UTF_8.name()

        minecraftVersion("1.20.6")
    }

    assemble {
        dependsOn(shadowJar)

        doLast {
            copy {
                from(shadowJar.get())
                into(rootProject.projectDir.resolve("jars"))
            }
        }
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")

        listOf(
            "com.ryderbelserion.vital"
        ).forEach {
            relocate(it, "libs.$it")
        }
    }

    processResources {
        inputs.properties("name" to rootProject.name)
        inputs.properties("version" to project.version)
        inputs.properties("group" to project.group)
        inputs.properties("authors" to project.properties["authors"])
        inputs.properties("description" to project.properties["description"])
        inputs.properties("website" to project.properties["website"])

        filesMatching("plugin.yml") {
            expand(inputs.properties)
        }
    }
}