package org.tokend.sdk.helpers.resourcegen

import org.tokend.sdk.factory.JsonApiToolsProvider
import java.io.File

/**
 * Generates resources from our OpenAPI docs using our Docker image
 *
 * @param tempDirectoryPath gitignored dir for temp generated files
 */
class DockerResourceGenerator(
        private val tempDirectoryPath: String
) {
    fun generateResources(openapiSpecFilePath: String,
                          namespace: String,
                          outputDirectoryPath: String,
                          ignoredKeys: Set<String> = emptySet(),
                          language: String = "java") {
        executeCommand("docker pull $GENERATOR_IMAGE")

        val ignoredKeysArgs = ignoredKeys.joinToString(
                separator = " ",
                transform = { "-I $it" }
        )

        // Put output to the temp directory in case of failure.
        val tempOutputPath = "$tempDirectoryPath/output"
        val tempOutputDir = File(tempOutputPath)
        tempOutputDir.mkdirs()
        tempOutputDir.deleteRecursively()

        if (openapiSpecFilePath.endsWith(".json")) {
            executeCommand("docker run --rm " +
                    "-v $openapiSpecFilePath:/resourcegen/openapi.json " +
                    "-v $tempOutputPath:/resourcegen/generated " +
                    "$GENERATOR_IMAGE " +
                    "--language $language " +
                    "--namespace $namespace " +
                    "$ignoredKeysArgs " +
                    "--json"
            )
        } else {
            executeCommand("docker run --rm " +
                    "-v $openapiSpecFilePath:/resourcegen/openapi.yaml " +
                    "-v $tempOutputPath:/resourcegen/generated " +
                    "$GENERATOR_IMAGE " +
                    "--language $language " +
                    "--namespace $namespace " +
                    "$ignoredKeysArgs "
            )
        }

        // Everything is OK so copy output to the actual target directory.
        val actualOutputDirectory = File(outputDirectoryPath)
        actualOutputDirectory.mkdirs()
        actualOutputDirectory.deleteRecursively()
        tempOutputDir.copyRecursively(
                target = actualOutputDirectory,
                overwrite = true
        )

        tempOutputDir.deleteRecursively()

        println("✅ Done! Do not forget to register new resources in " +
                "${JsonApiToolsProvider::class.java.simpleName} \uD83D\uDE09")
    }

    private fun executeCommand(command: String,
                               errorLinePredicate: (String) -> Boolean = { true }) {
        println("> $command")

        Runtime.getRuntime()
                .exec(command)
                .apply {
                    Thread {
                        inputStream.use { inputStream ->
                            inputStream.reader().forEachLine(System.out::println)
                        }
                    }.start()

                    Thread {
                        errorStream.use { errorStream ->
                            errorStream.reader().forEachLine {
                                if (errorLinePredicate(it)) {
                                    System.err.println(it)
                                }
                            }
                        }
                    }.start()

                    waitFor()

                    if (exitValue() != 0) {
                        throw Exception("Execution failed")
                    }
                }
    }

    private companion object {
        private const val GENERATOR_IMAGE = "registry.gitlab.com/tokend/mobile-openapi-resourcegen"
    }
}