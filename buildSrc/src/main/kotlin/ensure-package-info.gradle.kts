import tools.jackson.databind.json.JsonMapper
import tools.jackson.databind.node.ObjectNode
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.exists

plugins {
    id("java-library")
    id("idea")
}

tasks.register("ensurePackageInfo") {
    group = "Custom GT Tasks"
    description = "Ensures that package-info.java is present in every package in src/main/java. " +
            "However, it does not check any package-info.java file contents."
    doLast {
        val packagesThatDontHavePackageInfo = ArrayList<String>()

        file("src/main/java").walk()
            .filter { file -> file.isDirectory }
            .forEach { directory ->
                if (directory.listFiles().any { file -> file.isFile } &&
                    directory.listFiles().none { file -> file.name.equals("package-info.java") }) {
                    packagesThatDontHavePackageInfo.add(directory.toRelativeString(project.projectDir))
                }
            }

        if (packagesThatDontHavePackageInfo.isNotEmpty()) {
            throw RuntimeException(
                "There are package directories that do not contain package-info.java:\n" +
                        packagesThatDontHavePackageInfo.joinToString(separator = "\n")
            )
        }
    }
}

tasks.compileJava {
    dependsOn("ensurePackageInfo")
}
