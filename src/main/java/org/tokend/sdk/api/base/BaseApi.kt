package org.tokend.sdk.api.base

import org.tokend.sdk.utils.CookieJarProvider
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.factory.ServiceFactory

/**
 * Represent a base TokenD API.
 */
abstract class BaseApi
@JvmOverloads constructor(
        val rootUrl: String,
        protected val requestSigner: RequestSigner? = null,
        protected val tfaCallback: TfaCallback? = null,
        protected val cookieJarProvider: CookieJarProvider? = null,
        protected val userAgent: String? = null
) {
    protected val serviceFactory = ServiceFactory(rootUrl, userAgent)

    open val isSigned: Boolean
        get() = requestSigner != null

    open val signerAccountId: String?
        get() = requestSigner?.accountId

    protected open fun <T> getService(serviceClass: Class<T>): T {
        return serviceFactory.getCustomService(
                serviceClass,
                requestSigner,
                tfaCallback,
                cookieJarProvider
        )
    }
}