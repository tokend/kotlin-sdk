package org.tokend.sdk.test.jsonapi.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.general.model.SystemInfo
import org.tokend.sdk.test.Util
import org.tokend.wallet.Base32Check

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SystemInfoTest {
    @Test
    fun aGetSystemInfo() {
        val api = Util.getApi()

        val systemInfo = api.ingester.info.get().execute().get()

        val coreLedgerState = systemInfo.core

        if (coreLedgerState == null) {
            Assert.fail("Ledger state '${SystemInfo.LEDGER_CORE}' is null")
            return
        }

        Assert.assertNotEquals("Latest block can't be 0", 0, coreLedgerState.latest)
        Assert.assertNotNull("Passphrase can't be null", systemInfo.networkPassphrase)
        Assert.assertTrue("Admin account ID must be valid",
                Base32Check.isValid(
                        Base32Check.VersionByte.ACCOUNT_ID,
                        systemInfo.masterAccountId.toCharArray()
                )
        )
        Assert.assertEquals("Precision multiplier must be a power of 10", 0.0,
                Math.log10(systemInfo.precision.toDouble()) % 1, 0.0)
    }
}