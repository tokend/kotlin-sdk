package org.tokend.sdk.test.jsonapi

import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.history.model.ParticipantEffectsResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class ParticipantEffectsModelTest {

    @Test
    fun participantEffectsList() {
        val document = JsonApiToolsProvider().getResourceConverter().readDocumentCollection(
                participantEffectsResponse.toByteArray(),
                ParticipantEffectsResource::class.java
        )

        val entityList = document.get()

        assertTrue(entityList.isNotEmpty())

        val participantEffect = entityList.first()

        JsonApiUtil.checkResourceNullability(participantEffect)

        assertFalse(participantEffect.hasAttributes())
        //TODO: add includes after api refactoring
    }

    private val participantEffectsResponse = "{\n" +
            "   \"links\":{\n" +
            "      \"self\":\"/v2/history?page%5Bcursor%5D=0\\u0026page%5Blimit%5D=15\\u0026page%5Border%5D=asc\",\n" +
            "      \"prev\":\"/v2/history?page%5Bcursor%5D=0\\u0026page%5Blimit%5D=15\\u0026page%5Border%5D=desc\",\n" +
            "      \"next\":\"/v2/history?page%5Bcursor%5D=242120191377409\\u0026page%5Blimit%5D=15\\u0026page%5Border%5D=asc\"\n" +
            "   },\n" +
            "   \"data\":[\n" +
            "      {\n" +
            "         \"id\":\"242111601442817\",\n" +
            "         \"type\":\"participant-effects\",\n" +
            "         \"relationships\":{\n" +
            "            \"account\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"GDBTAGESMWHT2OISMGJ27HB6WQB2FVNEEIZL2SRBD2CXN26L6J4NKDP2\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"operation\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"242111601442817\",\n" +
            "                  \"type\":\"operations\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"effect\":null\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"242111601442818\",\n" +
            "         \"type\":\"participant-effects\",\n" +
            "         \"relationships\":{\n" +
            "            \"account\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"GDVRISEUHRJDLLRXYLKQDNT6OXWBP2QYYDRVX6IIKSNKAT7IRTVHAR2K\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"operation\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"242111601442817\",\n" +
            "                  \"type\":\"operations\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"effect\":null\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"242115896410113\",\n" +
            "         \"type\":\"participant-effects\",\n" +
            "         \"relationships\":{\n" +
            "            \"account\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"GDBTAGESMWHT2OISMGJ27HB6WQB2FVNEEIZL2SRBD2CXN26L6J4NKDP2\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"operation\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"242115896410113\",\n" +
            "                  \"type\":\"operations\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"effect\":null\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"id\":\"242120191377409\",\n" +
            "         \"type\":\"participant-effects\",\n" +
            "         \"relationships\":{\n" +
            "            \"account\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"GDVRISEUHRJDLLRXYLKQDNT6OXWBP2QYYDRVX6IIKSNKAT7IRTVHAR2K\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"operation\":{\n" +
            "               \"data\":{\n" +
            "                  \"id\":\"242120191377409\",\n" +
            "                  \"type\":\"operations\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"effect\":null\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}