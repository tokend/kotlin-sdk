package org.tokend.sdk.test.jsonapi

object Data {
    val accountResponse = "{  \n" +
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
            "         }\n" +
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
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val accountResponseUnincluded = "{  \n" +
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
            "         \"date\":\"2018-06-08T22:00:00Z\",\n" +
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
            "         }\n" +
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
            "   }\n" +
            "}"

    val balanceResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "      \"type\":\"balances\",\n" +
            "      \"attributes\":{  \n" +
            "         \"available\":\"0.000000\",\n" +
            "         \"locked\":\"0.000000\",\n" +
            "         \"require_review\":false\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"USD\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"account\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    val assetResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"assets\",\n" +
            "      \"id\":\"ETH\",\n" +
            "      \"attributes\":{  \n" +
            "         \"available_for_issuance\":\"0.000000\",\n" +
            "         \"preissued_asset_signer\":\"GAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHV4\",\n" +
            "         \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "         \"issued\":\"1000000000.000000\",\n" +
            "         \"pending_issuance\":\"0.000000\",\n" +
            "         \"policy_i\":1,\n" +
            "         \"policies\":[  \n" +
            "            {  \n" +
            "               \"name\":\"AssetPolicyTransferable\",\n" +
            "               \"value\":1\n" +
            "            },\n" +
            "            {  \n" +
            "               \"name\":\"AssetPolicyWithdrawable\",\n" +
            "               \"value\":8\n" +
            "            }\n" +
            "         ],\n" +
            "         \"details\":{  \n" +
            "            \"logo\":{  \n" +
            "               \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "               \"name\":\"ethereum_logo.png\",\n" +
            "               \"type\":\"image/png\"\n" +
            "            },\n" +
            "            \"name\":\"Ethereum\",\n" +
            "            \"terms\":{  \n" +
            "               \"key\":\"\",\n" +
            "               \"name\":\"\",\n" +
            "               \"type\":\"\"\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GDGQI3SSB7N7YDBGWCZB3DT7SA23KJWDTYQB5HCYR5VP3EBD6CXQXXG4\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"logo\":{  \n" +
            "            \"links\":{  \n" +
            "               \"related\":\"https://storage.com/key\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"terms\":{  \n" +
            "            \"links\":{  \n" +
            "               \"related\":\"https://storage.com/key\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    val assetListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/assets?page=3\",\n" +
            "      \"first\":\"https://api.com/assets?page=1\",\n" +
            "      \"prev\":\"https://api.com/assets?page=2\",\n" +
            "      \"next\":\"https://api.com/assets?page=4\",\n" +
            "      \"last\":\"https://api.com/assets?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"assets\",\n" +
            "         \"id\":\"ETH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available_for_issuance\":\"0.000000\",\n" +
            "            \"preissued_asset_signer\":\"GAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHV4\",\n" +
            "            \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "            \"issued\":\"1000000000.000000\",\n" +
            "            \"pending_issuance\":\"0.000000\",\n" +
            "            \"policy_i\":1,\n" +
            "            \"policies\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyTransferable\",\n" +
            "                  \"value\":1\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyBaseAsset\",\n" +
            "                  \"value\":2\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyWithdrawable\",\n" +
            "                  \"value\":8\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyTwoStepWithdrawal\",\n" +
            "                  \"value\":16\n" +
            "               }\n" +
            "            ],\n" +
            "            \"details\":{  \n" +
            "               \"external_system_type\":5,\n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "                  \"name\":\"ethereum_logo.png\",\n" +
            "                  \"type\":\"image/png\"\n" +
            "               },\n" +
            "               \"name\":\"Ethereum\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"owner\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GDGQI3SSB7N7YDBGWCZB3DT7SA23KJWDTYQB5HCYR5VP3EBD6CXQXXG4\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"logo\":{  \n" +
            "               \"links\":{  \n" +
            "                  \"related\":\"https://storage.com/key\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"terms\":{  \n" +
            "               \"links\":{  \n" +
            "                  \"related\":\"https://storage.com/key\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}