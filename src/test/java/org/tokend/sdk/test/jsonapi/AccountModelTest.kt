package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.balances.model.BalanceResource
import org.tokend.sdk.api.v2.kyc.model.KycResource
import org.tokend.sdk.api.v2.signers.model.SignerResource
import org.tokend.sdk.factory.JsonApiFactory

class AccountModelTest {

    @Test
    fun singleAccount() {

        val converter = ResourceConverter(
                JsonApiFactory().getBaseObjectMapper(),
                AccountResource::class.java,
                BalanceResource::class.java,
                SignerResource::class.java,
                KycResource::class.java
        )

        val document = converter.readDocument(
                accountResponse.toByteArray(),
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
                JsonApiFactory().getBaseObjectMapper(),
                AccountResource::class.java,
                BalanceResource::class.java,
                SignerResource::class.java,
                KycResource::class.java
        )

        val document = converter.readDocumentCollection(
                accountsListResponse.toByteArray(),
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

    private val accountResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\",\n" +
            "      \"type\":\"accounts\",\n" +
            "      \"attributes\":{  \n" +
            "         \"is_blocked\":false,\n" +
            "         \"block_reasons_i\":0,\n" +
            "         \"block_reasons\":[  \n" +
            "\n" +
            "         ],\n" +
            "         \"account_type\":\"AccountTypeSyndicate\",\n" +
            "         \"account_type_i\":6,\n" +
            "         \"date\":\"2018-06-08T22:42:00Z\",\n" +
            "         \"thresholds\":{  \n" +
            "            \"low_threshold\":0,\n" +
            "            \"med_threshold\":0,\n" +
            "            \"high_threshold\":0\n" +
            "         },\n" +
            "         \"policies\":{  \n" +
            "            \"type_i\":0,\n" +
            "            \"types\":[  \n" +
            "\n" +
            "            ]\n" +
            "         },\n" +
            "         \"limits\":{  \n" +
            "            \"daily_out\":\"100.000000\",\n" +
            "            \"weekly_out\":\"200.000000\",\n" +
            "            \"monthly_out\":\"300.000000\",\n" +
            "            \"annual_out\":\"400.000000\"\n" +
            "         },\n" +
            "         \"deposit_accounts\":[  \n" +
            "            {  \n" +
            "               \"address\":\"mqsJMEat8sJuEcYrxPfcheW47vTozMTV6D\",\n" +
            "               \"expires_at\":null,\n" +
            "               \"type\":\"1\"\n" +
            "            },\n" +
            "            {  \n" +
            "               \"address\":\"0xcA240552772229b5566a8A772F92158142f4f7aC\",\n" +
            "               \"expires_at\":null,\n" +
            "               \"type\":\"2\"\n" +
            "            },\n" +
            "            {  \n" +
            "               \"address\":\"0x0C0f09adE81f1671a6a73755D504c7595b8dDc89\",\n" +
            "               \"expires_at\":\"2018-08-18T17:30:37Z\",\n" +
            "               \"type\":\"4\"\n" +
            "            }\n" +
            "         ]\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"balances\":{  \n" +
            "            \"data\":[  \n" +
            "               {  \n" +
            "                  \"type\":\"balances\",\n" +
            "                  \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"type\":\"balances\",\n" +
            "                  \"id\":\"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"type\":\"balances\",\n" +
            "                  \"id\":\"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\"\n" +
            "               }\n" +
            "            ]\n" +
            "         },\n" +
            "         \"signers\":{  \n" +
            "            \"data\":[  \n" +
            "               {  \n" +
            "                  \"type\":\"signers\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            ]\n" +
            "         },\n" +
            "         \"kyc\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"kyc\",\n" +
            "               \"id\":\"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"referrer\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GBQ6JTEEMX5N3BBYDBTKCRMOFJOXHB7BV52FQCX6A4V4NH2DPEV3OUP6\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"referrals\":{  \n" +
            "            \"data\":[  \n" +
            "               {  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAXM5ONNYEQPVVH73IINMYTKOFGQMPDLYXBZ6JZRTJJARKQXDDLCAVZ7\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GA74HAO4I7JMUS3CQXBPVW7D3DO4V32UZE6CGNEVDJ3AIHXSOGZETX7J\"\n" +
            "               }\n" +
            "            ]\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"USD\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"ETH\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"signers\",\n" +
            "         \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\",\n" +
            "         \"attributes\":{  \n" +
            "            \"weight\":255,\n" +
            "            \"type_i\":1,\n" +
            "            \"types\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"SignerTypeReader\",\n" +
            "                  \"value\":1\n" +
            "               }\n" +
            "            ],\n" +
            "            \"identity\":0,\n" +
            "            \"name\":\"\"\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"kyc\",\n" +
            "         \"id\":\"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\",\n" +
            "         \"attributes\":{  \n" +
            "            \"first_name\":\"John\",\n" +
            "            \"last_name\":\"Doe\"\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    private val accountsListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/accounts?page=3\",\n" +
            "      \"first\":\"https://api.com/accounts?page=1\",\n" +
            "      \"prev\":\"https://api.com/accounts?page=2\",\n" +
            "      \"next\":\"https://api.com/accounts?page=4\",\n" +
            "      \"last\":\"https://api.com/accounts?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\",\n" +
            "         \"type\":\"accounts\",\n" +
            "         \"attributes\":{  \n" +
            "            \"is_blocked\":false,\n" +
            "            \"block_reasons_i\":0,\n" +
            "            \"block_reasons\":[  \n" +
            "\n" +
            "            ],\n" +
            "            \"account_type\":\"AccountTypeSyndicate\",\n" +
            "            \"account_type_i\":6,\n" +
            "            \"thresholds\":{  \n" +
            "               \"low_threshold\":0,\n" +
            "               \"med_threshold\":0,\n" +
            "               \"high_threshold\":0\n" +
            "            },\n" +
            "            \"policies\":{  \n" +
            "               \"type_i\":0,\n" +
            "               \"types\":[  \n" +
            "\n" +
            "               ]\n" +
            "            },\n" +
            "            \"limits\":{  \n" +
            "               \"daily_out\":\"100.000000\",\n" +
            "               \"weekly_out\":\"200.000000\",\n" +
            "               \"monthly_out\":\"300.000000\",\n" +
            "               \"annual_out\":\"400.000000\"\n" +
            "            },\n" +
            "            \"deposit_accounts\":[  \n" +
            "               {  \n" +
            "                  \"address\":\"mqsJMEat8sJuEcYrxPfcheW47vTozMTV6D\",\n" +
            "                  \"expires_at\":null,\n" +
            "                  \"type\":\"1\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"address\":\"0xcA240552772229b5566a8A772F92158142f4f7aC\",\n" +
            "                  \"expires_at\":null,\n" +
            "                  \"type\":\"2\"\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"address\":\"0x0C0f09adE81f1671a6a73755D504c7595b8dDc89\",\n" +
            "                  \"expires_at\":\"2018-08-18T17:30:37Z\",\n" +
            "                  \"type\":\"4\"\n" +
            "               }\n" +
            "            ]\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"balances\":{  \n" +
            "               \"data\":[  \n" +
            "                  {  \n" +
            "                     \"type\":\"balances\",\n" +
            "                     \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\"\n" +
            "                  },\n" +
            "                  {  \n" +
            "                     \"type\":\"balances\",\n" +
            "                     \"id\":\"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\"\n" +
            "                  },\n" +
            "                  {  \n" +
            "                     \"type\":\"balances\",\n" +
            "                     \"id\":\"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            },\n" +
            "            \"signers\":{  \n" +
            "               \"data\":[  \n" +
            "                  {  \n" +
            "                     \"type\":\"signers\",\n" +
            "                     \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            },\n" +
            "            \"kyc\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"kyc\",\n" +
            "                  \"id\":\"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"referrer\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GBQ6JTEEMX5N3BBYDBTKCRMOFJOXHB7BV52FQCX6A4V4NH2DPEV3OUP6\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"referrals\":{  \n" +
            "               \"data\":[  \n" +
            "                  {  \n" +
            "                     \"type\":\"accounts\",\n" +
            "                     \"id\":\"GAXM5ONNYEQPVVH73IINMYTKOFGQMPDLYXBZ6JZRTJJARKQXDDLCAVZ7\"\n" +
            "                  },\n" +
            "                  {  \n" +
            "                     \"type\":\"accounts\",\n" +
            "                     \"id\":\"GA74HAO4I7JMUS3CQXBPVW7D3DO4V32UZE6CGNEVDJ3AIHXSOGZETX7J\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"USD\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"balances\",\n" +
            "         \"id\":\"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"ETH\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\",\n" +
            "         \"type\":\"signers\",\n" +
            "         \"attributes\":{  \n" +
            "            \"weight\":255,\n" +
            "            \"type_i\":1,\n" +
            "            \"types\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"SignerTypeReader\",\n" +
            "                  \"value\":1\n" +
            "               }\n" +
            "            ],\n" +
            "            \"identity\":0,\n" +
            "            \"name\":\"\"\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"id\":\"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\",\n" +
            "         \"type\":\"kyc\",\n" +
            "         \"attributes\":{  \n" +
            "            \"first_name\":\"John\",\n" +
            "            \"last_name\":\"Doe\"\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}