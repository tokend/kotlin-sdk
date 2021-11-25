package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.test.Config
import org.tokend.sdk.test.Util
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CallbackThreadTest {
    @Test
    fun aDefaultExecutor() {
        val api = Util.getApi()

        var threadName = ""
        val latch = CountDownLatch(1)

        api.v3.info.getInfo().executeAsync(
                onSuccess = {
                    threadName = Thread.currentThread().name
                    latch.countDown()
                },
                onError = {
                    threadName = Thread.currentThread().name
                    latch.countDown()
                }
        )

        latch.await()

        Assert.assertEquals(
                "Async callback execution thread name must be a default one",
                BaseApi.ASYNC_CALLBACK_EXECUTION_THREAD_NAME,
                threadName
        )
    }

    @Test
    fun bOwnExecutor() {
        var hasBeenExecutedInOwnExecutor = false
        val ownExecutor = Executor {
            it.run()
            hasBeenExecutedInOwnExecutor = true
        }

        val api = TokenDApi(
                rootUrl = Config.API_URL,
                asyncCallbackExecutor = ownExecutor
        )

        val latch = CountDownLatch(1)

        api.v3.info.getInfo().executeAsync(
                onSuccess = { latch.countDown() },
                onError = { latch.countDown() }
        )

        latch.await()

        Assert.assertTrue(
                "Async callback must be executed in own executor",
                hasBeenExecutedInOwnExecutor
        )
    }
}