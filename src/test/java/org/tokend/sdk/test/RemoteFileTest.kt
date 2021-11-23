package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.model.RemoteFile
import org.tokend.sdk.api.base.model.isReallyNullOrNullAccordingToTheJavascript
import org.tokend.sdk.factory.JsonApiToolsProvider

class RemoteFileTest {
    @Test
    fun parseAndCompare() {
        val json = """
            {
              "key": "dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
              "name": "sample.pdf",
              "mime_type": "application/pdf"
            }
        """.trimIndent()

        val remoteFileJson = JsonApiToolsProvider.getObjectMapper().readValue(json, RemoteFile::class.java)
        Assert.assertEquals(
                "dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
                remoteFileJson.key
        )
        Assert.assertEquals(
                "sample.pdf",
                remoteFileJson.name
        )
        Assert.assertEquals(
                "application/pdf",
                remoteFileJson.mimeType
        )

        val remoteFileJackson = JsonApiToolsProvider.getObjectMapper().readValue(json, RemoteFile::class.java)
        Assert.assertEquals(
                "dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
                remoteFileJackson.key
        )
        Assert.assertEquals(
                "sample.pdf",
                remoteFileJackson.name
        )
        Assert.assertEquals(
                "application/pdf",
                remoteFileJackson.mimeType
        )

        Assert.assertEquals(remoteFileJson, remoteFileJackson)
    }

    @Test
    fun getUrl() {
        val file = RemoteFile(
                key = "dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
                name = "file.pdf",
                mimeType = "application/pdf"
        )

        Assert.assertEquals(
                "Trailing slash must be added if required",
                "https://tokend.io/storage/dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
                file.getUrl("https://tokend.io/storage")
        )

        Assert.assertEquals(
                "https://tokend.io/storage/dpurex4inf5nahjrsqkkimns6ascqpnddoe2roficpj7xtqorlvw4jd3lsglzzh4a4ctkaxuigqyht6i3t2usyu2",
                file.getUrl("https://tokend.io/storage/")
        )
    }

    @Test
    fun isImage() {
        val imageFiles = listOf(
                RemoteFile(
                        key = "k", name = "n", mimeType = "image/jpeg"
                ),
                RemoteFile(
                        key = "k", name = "n", mimeType = "image/png"
                )
        )
        val notImage = RemoteFile(
                key = "k", name = "n", mimeType = "application/pdf"
        )

        imageFiles.forEach {
            Assert.assertTrue(it.isImage)
        }

        Assert.assertFalse(notImage.isImage)
    }

    @Test
    fun javascript() {
        val fakeNullJsFile = RemoteFile(
                key = "", name = "", mimeType = ""
        )

        val nullFile: RemoteFile? = null

        Assert.assertTrue(fakeNullJsFile.isReallyNullOrNullAccordingToTheJavascript)
        Assert.assertTrue(nullFile.isReallyNullOrNullAccordingToTheJavascript)
    }
}