plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // Jackson for JSON parsing
    implementation("tools.jackson.core:jackson-core:3.0.0-rc8")
    implementation("tools.jackson.core:jackson-databind:3.0.0-rc8")
}
