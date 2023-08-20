plugins {
    id("root-plugin")
}

defaultTasks("build")

rootProject.group = "com.badbones69.blockparticles"
rootProject.description = "Sick of boring blocks? Today the day that changes, Add particles to your blocks now!"
rootProject.version = "1.12"

tasks {
    assemble {
        val jarsDir = File("$rootDir/jars")
        if (jarsDir.exists()) jarsDir.delete()

        subprojects.forEach { project ->
            dependsOn(":${project.name}:build")

            doLast {
                if (!jarsDir.exists()) jarsDir.mkdirs()

                if (project.name == "common") return@doLast

                val file = file("${project.buildDir}/libs/${rootProject.name}-${rootProject.version}.jar")

                copy {
                    from(file)
                    into(jarsDir)
                }
            }
        }
    }
}