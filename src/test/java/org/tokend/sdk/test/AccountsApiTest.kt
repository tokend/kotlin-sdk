package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test

class AccountsApiTest {
    @Test
    fun getById() {
        val api = Util.getSignedApi()

        val accountId = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .masterExchangeAccountId

        val account = api
                .accounts
                .getById(accountId)
                .execute()
                .get()

        Assert.assertEquals(accountId, account.accountId)
        Util.checkNullabilityViolations(account)
    }

    @Test
    fun getSigners() {
        val api = Util.getSignedApi()

        val accountId = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .masterExchangeAccountId

        val signers = api
                .accounts
                .getSigners(accountId)
                .execute()
                .get()

        Assert.assertTrue(signers.isNotEmpty())
        Util.checkNullabilityViolations(signers)
    }

    @Test
    fun getBalances() {
        val api = Util.getSignedApi()

        val accountId = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .masterExchangeAccountId

        val balances = api
                .accounts
                .getBalances(accountId)
                .execute()
                .get()

        Assert.assertTrue(balances.isNotEmpty())
        Util.checkNullabilityViolations(balances)
    }

    @Test
    fun getBalancesDetails() {
        val api = Util.getSignedApi()

        val accountId = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .masterExchangeAccountId

        val balancesDetails = api
                .accounts
                .getBalancesDetails(accountId)
                .execute()
                .get()

        Assert.assertTrue(balancesDetails.isNotEmpty())
        Util.checkNullabilityViolations(balancesDetails)
    }

    @Test
    fun getRawPayments() {
        val api = Util.getSignedApi()

        val accountId = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .masterExchangeAccountId

        val operations = api
                .accounts
                .getRawPayments(accountId)
                .execute()
                .get()
                .items

        Assert.assertTrue(operations.isNotEmpty())
        Util.checkNullabilityViolations(operations)
    }
}