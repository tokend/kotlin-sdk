package org.tokend.sdk.api.requests

import okhttp3.CookieJar

interface CookieJarProvider {
    fun getCookieJar(): CookieJar
}