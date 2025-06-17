plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.gradle.userdev") version "7.0.185"
    id("idea")
}

repositories {
    mavenLocal()
}

version = project.property("mod_version") as String
group = project.property("mod_group_id") as String

base {
    archivesName = project.property("mod_id") as String
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

runs {
    configureEach {
        systemProperty("forge.logging.markers", "REGISTRIES")
        systemProperty("forge.logging.console.level", "debug")
        modSource(project.sourceSets.main.get())
    }

    create("client") {
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
    }

    create("server") {
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
        argument("--nogui")
    }

    create("gameTestServer") {
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
    }

    create("data") {
        arguments.addAll(
            "--mod",
            project.property("mod_id") as String,
            "--all",
            "--output",
            file("src/generated/resources/").absolutePath,
            "--existing",
            file("src/main/resources/").absolutePath
        )
    }
}

sourceSets {
    main {
        resources {
            srcDir("src/generated/resources")
        }
    }
}

dependencies {
    implementation("net.neoforged:neoforge:${project.property("neo_version")}")
}

tasks.withType<ProcessResources>().configureEach {
    val replaceProperties = mapOf(
        "minecraft_version"       to project.property("minecraft_version") as String,
        "minecraft_version_range" to project.property("minecraft_version_range") as String,
        "neo_version"             to project.property("neo_version") as String,
        "neo_version_range"       to project.property("neo_version_range") as String,
        "loader_version_range"    to project.property("loader_version_range") as String,
        "mod_id"                  to project.property("mod_id") as String,
        "mod_name"                to project.property("mod_name") as String,
        "mod_license"             to project.property("mod_license") as String,
        "mod_version"             to project.property("mod_version") as String,
        "mod_authors"             to project.property("mod_authors") as String,
        "mod_description"         to project.property("mod_description") as String
    )
    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/neoforge.mods.toml")) {
        expand(replaceProperties)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

tasks {
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
    }
}
