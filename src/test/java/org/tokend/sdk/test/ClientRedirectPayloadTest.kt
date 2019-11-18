package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.redirects.ClientRedirectPayload
import org.tokend.sdk.redirects.ClientRedirectType

class ClientRedirectPayloadTest {
    @Test
    fun parse() {
        val url = "https://api.tokend.io/r/eyJ0eXBlIjoiYXBpLXZlcmlmeSIsIm1ldGEiOnsidG9rZW4iOiJ4aHhLUUZEYUZwTFNqRmJjWG9FRiIsIndhbGxldF9pZCI6IjkzMWRmODc4ODVjZGI4YWE4MDU4ODY4OTFhNDhmZDEzNTA2ODU3YWZlNjhmZjJjNDg0NjRiYTBjZjkzYTI0MTgifX0"
        val parsed = ClientRedirectPayload.fromUrl(url)!!
        Assert.assertEquals(ClientRedirectType.EMAIL_VERIFICATION, parsed.type)
        Assert.assertEquals(
                "xhxKQFDaFpLSjFbcXoEF",
                parsed.meta["token"].asString
        )
        Assert.assertEquals(
                "931df87885cdb8aa805886891a48fd13506857afe68ff2c48464ba0cf93a2418",
                parsed.meta["wallet_id"].asString
        )
    }
}