// There are two en_us.json files - manual and generated one.
// The problem is that Gradle can't merge them and gives an error.
// This convention plugin excludes en_us.json files from Gradle's copying,
// merges them together, and creates a merged en_us.json file in AbstractCopyTask's output folder for Gradle to use.

import tools.jackson.databind.json.JsonMapper
import tools.jackson.databind.node.ObjectNode
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

plugins {
    id("java-library")
    id("maven-publish")
    id("idea")
}

tasks.withType<ProcessResources>().configureEach {
    val (fileInAssetsPath: Path, fileInGeneratedPath: Path) = getEnUsFilePaths()

    // If any of the en_us.json files don't exist, we don't need to merge anything
    if (fileInAssetsPath.exists() && fileInGeneratedPath.exists()) {
        // Exclude the en_us.json file,
        // so that the AbstractCopyTask does not find two en_us.json files in normal resources and generated resources
        excludes.add("assets/gregtech6port/lang/en_us.json")

        doLast {
            val mergedFileContents: String = mergeJsonFiles(fileInAssetsPath, fileInGeneratedPath)

            if (!destinationDir.toPath().isAbsolute) { // Just in case
                println("destinationDirPath is not absolute. destinationDirPath=${destinationDir.toPath()}")
            }
            val mergedFilePath: Path = destinationDir.toPath().resolve("assets/gregtech6port/lang/en_us.json")

            // Let's hope that nothing wrong gets overwritten (it should not)
            Files.createDirectories(mergedFilePath.parent)
            Files.writeString(mergedFilePath, mergedFileContents)
        }
    }
}

fun getEnUsFilePaths(): Pair<Path, Path> {
    if (sourceSets.main.get().resources.sourceDirectories.files.size != 2) { // Just in case
        throw RuntimeException("Expected the main sourceSet resources sourceDirectories count to be 2, but it isn't")
    }

    val normalAssetsPath = sourceSets.main.get().resources.sourceDirectories.files
        .single { file -> file.path.contains("/src/main/resources") }.toPath()

    val generatedAssetsPath = sourceSets.main.get().resources.sourceDirectories.files
        .single { file -> file.path.contains("/src/generated/resources") }.toPath()

    if (!normalAssetsPath.isAbsolute || !generatedAssetsPath.isAbsolute) { // Just in case
        throw RuntimeException("Expected the paths to be absolute, but they are not")
    }

    val fileInAssetsPath = normalAssetsPath.resolve("assets/gregtech6port/lang/en_us.json")
    val fileInGeneratedPath = generatedAssetsPath.resolve("assets/gregtech6port/lang/en_us.json")

    return Pair(fileInAssetsPath, fileInGeneratedPath)
}

fun mergeJsonFiles(firstPath: java.nio.file.Path, secondPath: java.nio.file.Path): String {
    val jsonMapper = JsonMapper()

    val firstContents = jsonMapper.readValue(firstPath, ObjectNode::class.java)
    val secondContents = jsonMapper.readValue(secondPath, ObjectNode::class.java)

    val result = jsonMapper.createObjectNode()

    for (property in firstContents.properties()) {
        result.put(property.key, property.value.stringValue())
    }

    for (property in secondContents.properties()) {
        result.put(property.key, property.value.stringValue())
    }

    return jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
}
