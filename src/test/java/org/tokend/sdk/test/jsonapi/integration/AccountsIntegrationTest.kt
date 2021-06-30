package org.tokend.sdk.test.jsonapi.integration

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.tokend.sdk.api.v3.accounts.params.AccountParamsV3
import org.tokend.sdk.test.Util
import org.tokend.sdk.test.jsonapi.JsonApiUtil

class AccountsIntegrationTest {
    private lateinit var accountId: String

    @Before
    fun getAccountId() {
        accountId = Util.getApi().v3.general.getSystemInfo()
                .execute()
                .get()
                .masterAccountId
    }

    @Test
    fun getById() {
        val api = Util.getApi()

        val account = api
                .v3
                .accounts
                .getById(accountId)
                .execute()
                .get()

        JsonApiUtil.checkResourceNullability(account)
    }

    @Test
    fun getByIdWithIncludes() {
        val api = Util.getSignedApi()

        val account = api
                .v3
                .accounts
                .getById(
                        accountId,
                        AccountParamsV3(include = listOf(
                                AccountParamsV3.Includes.BALANCES,
                                AccountParamsV3.Includes.BALANCES_STATE,
                                AccountParamsV3.Includes.REFERRER,
                                AccountParamsV3.Includes.FEES,
                                AccountParamsV3.Includes.LIMITS,
                                AccountParamsV3.Includes.EXTERNAL_SYSTEM_IDS
                        ))
                )
                .execute()
                .get()

        JsonApiUtil.checkResourceNullability(account)
    }

    @Test
    fun getBalances() {
        val api = Util.getSignedApi()

        val balances = api
                .v3
                .accounts
                .getBalances(accountId)
                .execute()
                .get()

        Assert.assertTrue("No balances found", balances.isNotEmpty())
        JsonApiUtil.checkResourceNullability(balances)
    }

    @Test
    fun getFees() {
        val api = Util.getSignedApi()

        val fees = api
                .v3
                .accounts
                .getFees(accountId)
                .execute()
                .get()

        Assert.assertTrue("No fees found", fees.isNotEmpty())
        JsonApiUtil.checkResourceNullability(fees)
    }
}