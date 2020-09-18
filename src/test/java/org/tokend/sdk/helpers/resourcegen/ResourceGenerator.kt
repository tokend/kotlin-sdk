package org.tokend.sdk.helpers.resourcegen

import org.tokend.sdk.factory.JsonApiToolsProvider
import java.io.File

/**
 * Generates resources from our OpenAPI docs through many interesting steps...
 *
 * @param resourcegenPath full path of 'generator_java.rb'
 * @param tempOutputDirectoryPath gitignored directory for resourcegen outputs
 * @param openApiGeneratorDirectoryPath directory path of 'openapi-go-generator'
 */
class ResourceGenerator(
        private val resourcegenPath: String,
        private val tempOutputDirectoryPath: String,
        private val openApiGeneratorDirectoryPath: String
) {
    /**
     * @param docsDirectoryPath OpenAPI docs directory, usually it's 'xxx/docs'
     * @param openApiYamlFilePath path of 'openapi.yaml', usually it's 'docs/web_deploy/openapi.yaml'
     * @param resourceGroupName used to create temp files and dirs in [tempOutputDirectoryPath]
     * @param generatedClassesDirectoryPath where to write Java classes
     * @param generatedClassesNamespace namespace for generated classes, must match [generatedClassesDirectoryPath]
     * @param ignoredKeys OpenApi keys (i.e. 'PaymentOpKey') for which no resources and inners will be generated
     * @param buildWithMake use 'make' for build instead of NodeJS
     */
    fun generateResources(docsDirectoryPath: String,
                          openApiYamlFilePath: String,
                          resourceGroupName: String,
                          generatedClassesDirectoryPath: String,
                          generatedClassesNamespace: String,
                          ignoredKeys: Set<String> = emptySet(),
                          buildWithMake: Boolean = false) {
        File(tempOutputDirectoryPath).mkdir()

        val intermediateFilePath = "$tempOutputDirectoryPath/${resourceGroupName}_intermediate.yaml"
        val specsDirectoryPath = "$tempOutputDirectoryPath/${resourceGroupName}_specs"

        buildDocs(docsDirectoryPath, buildWithMake)
        generateIntermediate(openApiYamlFilePath, intermediateFilePath)
        convertIntermediateToSpecs(intermediateFilePath, specsDirectoryPath, ignoredKeys)
        generateClassesFromSpecs(specsDirectoryPath, generatedClassesDirectoryPath, generatedClassesNamespace)

        println("✅ Done! Do not forget to register new resources in " +
                "${JsonApiToolsProvider::class.java.simpleName} \uD83D\uDE09")
    }

    private fun buildDocs(docsDirectoryPath: String,
                          buildWithMake: Boolean) {
        println("Building docs at '$docsDirectoryPath'...\n")

        if (buildWithMake) {
            buildDocsWithMake(docsDirectoryPath)
        } else {
            buildDocsWithNode(docsDirectoryPath)
        }

        println()
    }

    private fun buildDocsWithMake(docsDirectoryPath: String) {
        executeCommand("cd $docsDirectoryPath && make")
    }

    private fun buildDocsWithNode(docsDirectoryPath: String) {
        executeCommand("yarn --cwd $docsDirectoryPath run build")
    }

    private fun generateIntermediate(openApiYamlFilePath: String,
                                     intermediateFileOutputPath: String) {
        println("Creating intermediate from OpenAPI '$openApiYamlFilePath' to '$intermediateFileOutputPath'...\n")

        executeCommand(
                command = "python3 $openApiGeneratorDirectoryPath/intermediate.py " +
                        "--path $openApiYamlFilePath --out $intermediateFileOutputPath ",
                errorLinePredicate = { !it.startsWith("WARNING:") }
        )

        println()
    }

    private fun convertIntermediateToSpecs(intermediateFilePath: String,
                                           outputDirectoryPath: String,
                                           ignoredKeys: Set<String>) {
        println("Converting intermediate '$intermediateFilePath' to specs '$outputDirectoryPath'...\n")

        val openApi = OpenApiIntermediateParser().parse(intermediateFilePath, ignoredKeys)
        val specs = YamlSpecsGenerator(openApi).generate()

        File(outputDirectoryPath).deleteRecursively()

        File("$outputDirectoryPath/inner").mkdirs()
        File("$outputDirectoryPath/resources").mkdirs()

        specs.inner.forEach { (name, content) ->
            File("$outputDirectoryPath/inner/$name.yaml").outputStream().use { fileOutputStream ->
                fileOutputStream.write(content.toByteArray())
            }
        }

        specs.resources.forEach { (name, content) ->
            File("$outputDirectoryPath/resources/$name.yaml").outputStream().use { fileOutputStream ->
                fileOutputStream.write(content.toByteArray())
            }
        }

        println()
    }

    private fun generateClassesFromSpecs(specsDirectoryPath: String,
                                         outputDirectoryPath: String,
                                         namespace: String) {
        println("Generating classes from specs '$specsDirectoryPath' to '$outputDirectoryPath'...\n")

        executeCommand("ruby $resourcegenPath " +
                "--specs $specsDirectoryPath --out $outputDirectoryPath --namespace $namespace")

        println()
    }

    private fun executeCommand(command: String,
                               errorLinePredicate: (String) -> Boolean = { true }) {
        println("> $command")

        Runtime.getRuntime()
                .exec(command)
                .apply {
                    inputStream.use { inputStream ->
                        inputStream.reader().forEachLine(System.out::println)
                    }
                    errorStream.use { errorStream ->
                        errorStream.reader().forEachLine {
                            if (errorLinePredicate(it)) {
                                System.err.println(it)
                            }
                        }
                    }

                    waitFor()

                    if (exitValue() != 0) {
                        throw Exception("Execution failed")
                    }
                }
    }
}