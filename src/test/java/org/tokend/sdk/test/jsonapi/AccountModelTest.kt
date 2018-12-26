package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.balances.model.BalanceResource
import org.tokend.sdk.api.v2.kyc.model.KycResource
import org.tokend.sdk.api.v2.signers.model.SignerResource

class AccountModelTest {

    @Test
    fun singleAccount() {

        val converter = ResourceConverter(
                AccountResource::class.java,
                BalanceResource::class.java,
                SignerResource::class.java,
                KycResource::class.java
        )

        val document = converter.readDocument(
                Data.accountResponse.toByteArray(),
                AccountResource::class.java
        )

        val account = document.get()

        JsonApiUtil.checkResourceNullability(account)

        assertTrue(account.hasAttributes())
        assertTrue(account.balances.first().hasAttributes())
        assertTrue(account.signers.first().hasAttributes())
        assertTrue(account.kyc.hasAttributes())
        assertFalse(account.referrer.hasAttributes())
        assertFalse(account.referrals.first().hasAttributes())
        assertNotNull(account.limits.annualOut)
    }

    @Test
    fun accountsList() {

        val converter = ResourceConverter(
                AccountResource::class.java,
                BalanceResource::class.java,
                SignerResource::class.java,
                KycResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.accountsListResponse.toByteArray(),
                AccountResource::class.java
        )

        assertNotNull(document.meta)
        assertNotNull(document.links)

        val accounts = document.get()

        JsonApiUtil.checkResourceNullability(accounts)

        assertTrue(accounts.isNotEmpty())

        val account = accounts.first()

        assertTrue(account.hasAttributes())
        assertTrue(account.balances.first().hasAttributes())
        assertTrue(account.signers.first().hasAttributes())
        assertTrue(account.kyc.hasAttributes())
        assertFalse(account.referrer.hasAttributes())
        assertFalse(account.referrals.first().hasAttributes())
        assertNotNull(account.limits.annualOut)
    }
}