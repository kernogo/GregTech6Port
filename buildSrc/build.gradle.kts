plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // Jackson for JSON parsing
    implementation("tools.jackson.core:jackson-core:3.0.4")
    implementation("tools.jackson.core:jackson-databind:3.0.4")
}
