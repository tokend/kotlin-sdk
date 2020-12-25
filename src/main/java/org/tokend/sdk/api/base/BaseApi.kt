package org.tokend.sdk.api.base

import org.tokend.sdk.factory.ServiceFactory
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Represent a base TokenD API.
 */
abstract class BaseApi
@JvmOverloads constructor(
        val rootUrl: String,
        val requestSigner: RequestSigner? = null,
        protected val tfaCallback: TfaCallback? = null,
        protected val cookieJarProvider: CookieJarProvider? = null,
        extraHeaders: Map<String, String?>? = null,
        asyncCallbackExecutor: Executor = DEFAULT_ASYNC_CALLBACK_EXECUTOR,
        withLogs: Boolean
) {
    protected val serviceFactory = ServiceFactory(rootUrl, withLogs,
            asyncCallbackExecutor, extraHeaders)

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

    companion object {
        const val ASYNC_CALLBACK_EXECUTION_THREAD_NAME = "AsyncCallbackExecutionThread"
        val DEFAULT_ASYNC_CALLBACK_EXECUTOR: Executor = Executors.newCachedThreadPool {
            Thread(it).apply { name = ASYNC_CALLBACK_EXECUTION_THREAD_NAME }
        }
    }
}