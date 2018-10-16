package org.tokend.sdk.utils

import okhttp3.CookieJar

interface CookieJarProvider {
    fun getCookieJar(): CookieJar
}