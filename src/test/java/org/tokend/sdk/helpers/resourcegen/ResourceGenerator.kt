package org.tokend.sdk.helpers.resourcegen

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
     * @param buildWithMake use 'make' for build instead of NodeJS
     */
    fun generateResources(docsDirectoryPath: String,
                          openApiYamlFilePath: String,
                          resourceGroupName: String,
                          generatedClassesDirectoryPath: String,
                          generatedClassesNamespace: String,
                          buildWithMake: Boolean = false) {
        File(tempOutputDirectoryPath).mkdir()

        val intermediateFilePath = "$tempOutputDirectoryPath/${resourceGroupName}_intermediate.yaml"
        val specsDirectoryPath = "$tempOutputDirectoryPath/${resourceGroupName}_specs"

        buildDocs(docsDirectoryPath, buildWithMake)
        generateIntermediate(openApiYamlFilePath, intermediateFilePath)
        convertIntermediateToSpecs(intermediateFilePath, specsDirectoryPath)
        generateClassesFromSpecs(specsDirectoryPath, generatedClassesDirectoryPath, generatedClassesNamespace)

        println("✅ Done! Do not forget to register new resources ;)")
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
        ProcessBuilder()
                .directory(File(docsDirectoryPath))
                .command("make")
                .inheritIO()
                .start()
                .waitFor()
    }

    private fun buildDocsWithNode(docsDirectoryPath: String) {
        Runtime.getRuntime()
                .exec("yarn --cwd $docsDirectoryPath run build")
                .apply {
                    inputStream.use { inputStream ->
                        inputStream.reader().forEachLine(System.out::println)
                    }
                }
                .waitFor()
    }

    private fun generateIntermediate(openApiYamlFilePath: String,
                                     intermediateFileOutputPath: String) {
        println("Creating intermediate from OpenAPI '$openApiYamlFilePath' to '$intermediateFileOutputPath'...\n")

        Runtime.getRuntime()
                .exec("python3 $openApiGeneratorDirectoryPath/intermediate.py " +
                        "--path $openApiYamlFilePath --out $intermediateFileOutputPath ")
                .apply {
                    inputStream.use { inputStream ->
                        inputStream.reader().forEachLine(System.out::println)
                    }
                }
                .waitFor()

        println()
    }

    private fun convertIntermediateToSpecs(intermediateFilePath: String,
                                           outputDirectoryPath: String) {
        println("Converting intermediate '$intermediateFilePath' to specs '$outputDirectoryPath'...\n")

        val openApi = OpenApiIntermediateParser().parse(intermediateFilePath)
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

        Runtime.getRuntime()
                .exec("ruby $resourcegenPath " +
                        "--specs $specsDirectoryPath --out $outputDirectoryPath --namespace $namespace")
                .apply {
                    inputStream.use { inputStream ->
                        inputStream.reader().forEachLine(System.out::println)
                    }
                }
                .waitFor()

        println()
    }
}