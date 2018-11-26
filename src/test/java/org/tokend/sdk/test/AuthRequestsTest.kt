package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.authenticator.model.AuthRequest
import java.net.URLEncoder
import java.util.*

class AuthRequestsTest {
    @Test
    fun parseAuthRequestFromUri() {
        val api = "https://api.testnet.tokend.org"
        val app = "TokenD Web client"
        val publicKey = "GDPRXQXJSPVBVY4XHJORHPJFPEOTQUTF3AYHME2FH7CVQV5GO2BLIEO4"
        val scope = 268435455
        val expirationTimestamp = 1541930400
        val expirationDate = Date(expirationTimestamp * 1000L)

        val uri = "tokend://auth?api=${URLEncoder.encode(api)}" +
                "&app=${URLEncoder.encode(app)}" +
                "&pubkey=$publicKey" +
                "&scope=$scope" +
                "&expires_at=$expirationTimestamp"

        val authRequest = AuthRequest.parse(uri)

        Assert.assertEquals(api, authRequest.networkUrl)
        Assert.assertEquals(app, authRequest.appName)
        Assert.assertEquals(scope, authRequest.scope)
        Assert.assertEquals(publicKey, authRequest.publicKey)
        Assert.assertEquals(expirationDate, authRequest.expirationDate)
    }

    @Test
    fun buildAuthRequestUri() {
        val api = "https://api.testnet.tokend.org"
        val app = "TokenD Web client"
        val publicKey = "GDPRXQXJSPVBVY4XHJORHPJFPEOTQUTF3AYHME2FH7CVQV5GO2BLIEO4"
        val scope = 268435455
        val expirationTimestamp = 1541930400
        val expirationDate = Date(expirationTimestamp * 1000L)

        val authRequest = AuthRequest(api, app, scope, publicKey, expirationDate)
        val uri = authRequest.uriString

        AuthRequest.parse(uri)

        Assert.assertEquals("tokend://auth?api=https%3A%2F%2Fapi.testnet.tokend.org&app=TokenD+Web+client&pubkey=GDPRXQXJSPVBVY4XHJORHPJFPEOTQUTF3AYHME2FH7CVQV5GO2BLIEO4&scope=268435455&expires_at=1541930400",
                uri)

        Assert.assertEquals(api, authRequest.networkUrl)
        Assert.assertEquals(app, authRequest.appName)
        Assert.assertEquals(scope, authRequest.scope)
        Assert.assertEquals(publicKey, authRequest.publicKey)
        Assert.assertEquals(expirationDate, authRequest.expirationDate)
    }
}