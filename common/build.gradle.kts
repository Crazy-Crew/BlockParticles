plugins {
    id("root-plugin")
}

dependencies {
    compileOnlyApi(libs.bundles.adventure)

    compileOnly(libs.cluster.api)

    api(libs.cloud.core)
    api(libs.cloud.brig)
    api(libs.cloud.extras)

    api(libs.configme) {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
}