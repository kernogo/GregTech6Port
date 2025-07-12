plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.gradle.userdev") version "7.0.185"
    id("idea")
}

repositories {
    // These are not the only repositories in use,
    // there are also repositories automatically added by the NeoForged plugin
    mavenLocal()
    exclusiveContent {
        forRepository { mavenCentral() }
        filter { includeGroup("org.appliedenergistics") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://cursemaven.com/") } }
        filter { includeGroup("curse.maven") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://api.modrinth.com/maven/") } }
        filter { includeGroup("maven.modrinth") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.terraformersmc.com/") } }
        filter { includeGroup("dev.emi") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.blamejared.com/") } }
        filter { includeGroup("mezz.jei"); includeGroup("vazkii.patchouli") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.tamaized.com/releases/") } }
        filter { includeGroup("team-twilight") }
    }
    exclusiveContent {
        forRepository { maven { url = uri("https://maven.theillusivec4.top/") } }
        filter { includeGroup("top.theillusivec4.curios") }
    }
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
        jvmArguments.addAll("-Xmx3g")
    }

    create("client") {
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
        arguments.addAll("--username", "Dev")
    }

    create("client2") {
        runType("client")
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
        arguments.addAll("--username", "Dev2")
    }

    create("server") {
        systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id") as String)
        //argument("--nogui")
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

    // lombok
    compileOnly("org.projectlombok:lombok:${project.property("lombok_version")}")
    annotationProcessor("org.projectlombok:lombok:${project.property("lombok_version")}")
    testCompileOnly("org.projectlombok:lombok:${project.property("lombok_version")}")
    testAnnotationProcessor("org.projectlombok:lombok:${project.property("lombok_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/jei
    compileOnly("mezz.jei:jei-${project.property("minecraft_version")}-neoforge-api:${project.property("jei_version")}")
    runtimeOnly("mezz.jei:jei-${project.property("minecraft_version")}-neoforge:${project.property("jei_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/emi
    compileOnly("dev.emi:emi-neoforge:${project.property("emi_version")}:api")
    runtimeOnly("dev.emi:emi-neoforge:${project.property("emi_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/the-twilight-forest
    implementation("team-twilight:twilightforest:${project.property("twilightforest_version")}:universal")

    // https://www.curseforge.com/minecraft/mc-mods/applied-energistics-2
    compileOnly("org.appliedenergistics:appliedenergistics2:${project.property("ae2_version")}:api")
    runtimeOnly("org.appliedenergistics:appliedenergistics2:${project.property("ae2_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/railcraft-reborn
    compileOnly("curse.maven:railcraft-reborn-901491:${project.property("railcraft_api_curse_version")}")
    runtimeOnly("curse.maven:railcraft-reborn-901491:${project.property("railcraft_curse_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/curios
    compileOnly("top.theillusivec4.curios:curios-neoforge:${project.property("curios_version")}:api")
    runtimeOnly("top.theillusivec4.curios:curios-neoforge:${project.property("curios_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/jade
    implementation("maven.modrinth:jade:${project.property("jade_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/journeymap
    runtimeOnly("curse.maven:journeymap-32274:${project.property("journeymap_curse_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/patchouli
    runtimeOnly("vazkii.patchouli:Patchouli:${project.property("patchouli_version")}")

    // https://www.curseforge.com/minecraft/mc-mods/building-gadgets
    runtimeOnly("curse.maven:building-gadgets-298187:${project.property("building_gadgets_curse_version")}")
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
