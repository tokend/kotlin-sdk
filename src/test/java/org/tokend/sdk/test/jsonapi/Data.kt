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
            "      },\n" +
            "      { \n" +
            "           \"type\":\"signers\", \n" +
            "           \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\", \n" +
            "           \"attributes\":{  \n" +
            "               \"weight\": 255,\n" +
            "               \"type_i\": 1,\n" +
            "               \"types\": [\n" +
            "                   { \n" +
            "                       \"name\": \"SignerTypeReader\", \n" +
            "                       \"value\": 1 \n" +
            "                   } \n" +
            "               ], \n" +
            "               \"identity\": 0, \n" +
            "               \"name\": \"\" \n" +
            "            }\n" +
            "         }, \n" +
            "      { \n" +
            "        \"type\":\"kyc\", \n" +
            "        \"id\":\"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\", \n" +
            "         \"attributes\":{  \n" +
            "               \"first_name\": \"John\", \n" +
            "               \"last_name\": \"Doe\" \n" +
            "         }\n" +
            "      } \n" +
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

    val accountsListResponse = "{ \n" +
            "   \"meta\": {\n" +
            "       \"current_page\": 3, \n" +
            "       \"total_pages\": 10 \n" +
            "   }, \n" +
            "   \"links\": { \n" +
            "       \"self\": \"https://api.com/accounts?page=3\", \n" +
            "       \"first\": \"https://api.com/accounts?page=1\", \n" +
            "       \"prev\": \"https://api.com/accounts?page=2\", \n" +
            "       \"next\": \"https://api.com/accounts?page=4\", \n" +
            "       \"last\": \"https://api.com/accounts?page=13\" \n" +
            "   }, \n" +
            "   \"data\": [\n" +
            "       { \n" +
            "           \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\", \n" +
            "           \"type\": \"accounts\", \n" +
            "           \"attributes\": { \n" +
            "               \"is_blocked\": false, \n" +
            "               \"block_reasons_i\": 0, \n" +
            "               \"block_reasons\": [], \n" +
            "               \"account_type\": \"AccountTypeSyndicate\", \n" +
            "               \"account_type_i\": 6, \n" +
            "               \"thresholds\": { \n" +
            "                   \"low_threshold\": 0, \n" +
            "                   \"med_threshold\": 0, \n" +
            "                   \"high_threshold\": 0 \n" +
            "               }, \n" +
            "               \"policies\": { \n" +
            "                   \"type_i\": 0, \n" +
            "                   \"types\":[]\n" +
            "               }, \n" +
            "               \"limits\": { \n" +
            "                   \"daily_out\": \"100.000000\", \n" +
            "                   \"weekly_out\": \"200.000000\", \n" +
            "                   \"monthly_out\": \"300.000000\", \n" +
            "                   \"annual_out\": \"400.000000\" \n" +
            "               }, \n" +
            "               \"deposit_accounts\": [ \n" +
            "                   {   \n" +
            "                       \"address\": \"mqsJMEat8sJuEcYrxPfcheW47vTozMTV6D\", \n" +
            "                       \"expires_at\": null, \n" +
            "                       \"type\": \"1\" \n" +
            "                   }, \n" +
            "                   {   \n" +
            "                       \"address\": \"0xcA240552772229b5566a8A772F92158142f4f7aC\", \n" +
            "                       \"expires_at\": null, \n" +
            "                       \"type\": \"2\" \n" +
            "                   }, \n" +
            "                   {   \n" +
            "                       \"address\": \"0x0C0f09adE81f1671a6a73755D504c7595b8dDc89\", \n" +
            "                       \"expires_at\": \"2018-08-18T17:30:37Z\", \n" +
            "                       \"type\": \"4\" \n" +
            "                   } \n" +
            "               ] \n" +
            "           }, \n" +
            "           \"relationships\": { \n" +
            "               \"balances\": { \n" +
            "                   \"data\": [\n" +
            "                       {\n" +
            "                           \"type\": \"balances\", \n" +
            "                           \"id\": \"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\" \n" +
            "                       }, \n" +
            "                       { \n" +
            "                           \"type\": \"balances\", \n" +
            "                           \"id\": \"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\" \n" +
            "                       }, \n" +
            "                       { \n" +
            "                           \"type\": \"balances\", \n" +
            "                           \"id\": \"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\" \n" +
            "                       }\n" +
            "                   ]\n" +
            "               }, \n" +
            "               \"signers\": { \n" +
            "                   \"data\": [ \n" +
            "                       { \n" +
            "                           \"type\": \"signers\", \n" +
            "                           \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\" \n" +
            "                       } \n" +
            "                   ] \n" +
            "               }, \n" +
            "               \"kyc\": { \n" +
            "                   \"data\": { \n" +
            "                       \"type\": \"kyc\", \n" +
            "                       \"id\": \"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\" \n" +
            "                   } \n" +
            "               }, \n" +
            "               \"referrer\": { \n" +
            "                   \"data\": { \n" +
            "                       \"type\": \"accounts\", \n" +
            "                       \"id\": \"GBQ6JTEEMX5N3BBYDBTKCRMOFJOXHB7BV52FQCX6A4V4NH2DPEV3OUP6\" \n" +
            "                   } \n" +
            "               }, \n" +
            "               \"referrals\": {\n" +
            "                   \"data\": [ \n" +
            "                       { \n" +
            "                           \"type\": \"accounts\", \n" +
            "                           \"id\": \"GAXM5ONNYEQPVVH73IINMYTKOFGQMPDLYXBZ6JZRTJJARKQXDDLCAVZ7\" \n" +
            "                       }, \n" +
            "                       { \n" +
            "                           \"type\": \"accounts\", \n" +
            "                           \"id\": \"GA74HAO4I7JMUS3CQXBPVW7D3DO4V32UZE6CGNEVDJ3AIHXSOGZETX7J\" \n" +
            "                       } \n" +
            "                   ] \n" +
            "               } \n" +
            "           } \n" +
            "       }\n" +
            "   ],\n" +
            "   \"included\": [ \n" +
            "       {\n" +
            "           \"type\": \"balances\", \n" +
            "           \"id\": \"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\", \n" +
            "           \"attributes\": {\n" +
            "               \"available\": \"0.000000\", \n" +
            "               \"locked\": \"0.000000\", \n" +
            "               \"require_review\": false \n" +
            "           }, \n" +
            "           \"relationships\": { \n" +
            "               \"asset\": { \n" +
            "                   \"type\": \"assets\", \n" +
            "                   \"id\": \"USD\" \n" +
            "               } \n" +
            "           }\n" +
            "       },\n" +
            "       {\n" +
            "           \"type\": \"balances\", \n" +
            "           \"id\": \"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\", \n" +
            "           \"attributes\": { \n" +
            "               \"available\": \"0.000000\", \n" +
            "               \"locked\": \"0.000000\", \n" +
            "               \"require_review\": false \n" +
            "           }, \n" +
            "           \"relationships\": {\n" +
            "               \"asset\": {\n" +
            "               \"type\": \"assets\", \n" +
            "               \"id\": \"BTC\" \n" +
            "               }\n" +
            "           }\n" +
            "       },\n" +
            "       {\n" +
            "       \"type\": \"balances\", \n" +
            "       \"id\": \"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\", \n" +
            "       \"attributes\": {\n" +
            "           \"available\": \"0.000000\", \n" +
            "           \"locked\": \"0.000000\", \n" +
            "           \"require_review\": false \n" +
            "       },\n" +
            "       \"relationships\": { \n" +
            "           \"asset\": {\n" +
            "               \"type\": \"assets\", \n" +
            "               \"id\": \"ETH\" \n" +
            "               }\n" +
            "           }\n" +
            "       },\n" +
            "       {\n" +
            "       \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\", \n" +
            "       \"type\": \"signers\", \n" +
            "       \"attributes\": { \n" +
            "           \"weight\": 255, \n" +
            "           \"type_i\": 1, \n" +
            "               \"types\": [\n" +
            "                   { \n" +
            "                       \"name\": \"SignerTypeReader\", \n" +
            "                       \"value\": 1 \n" +
            "                   } \n" +
            "               ], \n" +
            "           \"identity\": 0, \n" +
            "           \"name\": \"\" \n" +
            "           }\n" +
            "       },\n" +
            "       {\n" +
            "           \"id\": \"BQJYZCADPKJRNUBN33MFMOUTKSASBANFHYBGZSF25KRP372NLU4Q\", \n" +
            "           \"type\": \"kyc\", \n" +
            "           \"attributes\": {\n" +
            "               \"first_name\": \"John\", \n" +
            "               \"last_name\": \"Doe\" \n" +
            "           }\n" +
            "       }\n" +
            "    ]\n" +
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

    val balanceListResponseUnincluded = "{\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"id\": \"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "      \"type\": \"balances\",\n" +
            "      \"attributes\": {\n" +
            "        \"available\": \"0.000000\",\n" +
            "        \"locked\": \"0.000000\",\n" +
            "        \"require_review\": false\n" +
            "      },\n" +
            "      \"relationships\": {\n" +
            "        \"asset\": {\n" +
            "          \"data\": {\n" +
            "            \"type\": \"assets\",\n" +
            "            \"id\": \"USD\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\",\n" +
            "      \"type\": \"balances\",\n" +
            "      \"attributes\": {\n" +
            "        \"available\": \"0.000000\",\n" +
            "        \"locked\": \"0.000000\",\n" +
            "        \"require_review\": false\n" +
            "      },\n" +
            "      \"relationships\": {\n" +
            "        \"asset\": {\n" +
            "          \"data\": {\n" +
            "            \"type\": \"assets\",\n" +
            "            \"id\": \"BTC\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\",\n" +
            "      \"type\": \"balances\",\n" +
            "      \"attributes\": {\n" +
            "        \"available\": \"0.000000\",\n" +
            "        \"locked\": \"0.000000\",\n" +
            "        \"require_review\": false\n" +
            "      },\n" +
            "      \"relationships\": {\n" +
            "        \"asset\": {\n" +
            "          \"data\": {\n" +
            "            \"type\": \"assets\",\n" +
            "            \"id\": \"ETH\"\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
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

    val assetPairResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"asset_pairs\",\n" +
            "      \"id\":\"BTC-HHCT\",\n" +
            "      \"attributes\":{  \n" +
            "         \"price\":\"1.000000\",\n" +
            "         \"policy_i\":1,\n" +
            "         \"policies\":[  \n" +
            "            {  \n" +
            "               \"name\":\"AssetPairPolicyTradeableSecondaryMarket\",\n" +
            "               \"value\":1\n" +
            "            }\n" +
            "         ]\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"base_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"HHCT\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"quote_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"BTC\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    val assetPairsListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/asset_pairs?page=3\",\n" +
            "      \"first\":\"https://api.com/asset_pairs?page=1\",\n" +
            "      \"prev\":\"https://api.com/asset_pairs?page=2\",\n" +
            "      \"next\":\"https://api.com/asset_pairs?page=4\",\n" +
            "      \"last\":\"https://api.com/asset_pairs?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"asset_pairs\",\n" +
            "         \"id\":\"BTC-HHCT\",\n" +
            "         \"attributes\":{  \n" +
            "            \"price\":\"1.000000\",\n" +
            "            \"policy_i\":0,\n" +
            "            \"policies\":[  \n" +
            "\n" +
            "            ]\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"base_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"HHCT\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"quote_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"asset_pairs\",\n" +
            "         \"id\":\"ETH-USD\",\n" +
            "         \"attributes\":{  \n" +
            "            \"current_price\":\"294.470000\",\n" +
            "            \"price\":\"294.470000\",\n" +
            "            \"policy_i\":0,\n" +
            "            \"policies\":[  \n" +
            "\n" +
            "            ]\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"base_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"HHCT\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"quote_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val feesListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/fees?page=3\",\n" +
            "      \"first\":\"https://api.com/fees?page=1\",\n" +
            "      \"prev\":\"https://api.com/fees?page=2\",\n" +
            "      \"next\":\"https://api.com/fees?page=4\",\n" +
            "      \"last\":\"https://api.com/fees?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"fees\",\n" +
            "         \"id\":\"eut123Mnwqe\",\n" +
            "         \"attributes\":{  \n" +
            "            \"fixed\":\"0.000000\",\n" +
            "            \"percent\":\"1.000000\",\n" +
            "            \"fee_asset\":\"USD\",\n" +
            "            \"applied_to\":{  \n" +
            "               \"asset\":\"BTC\",\n" +
            "               \"subtype\":0,\n" +
            "               \"fee_type\":4,\n" +
            "               \"account_id\":\"\",\n" +
            "               \"account_type\":-1,\n" +
            "               \"lower_bound\":\"0.000000\",\n" +
            "               \"upper_bound\":\"9223372036854.775807\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"account\":null,\n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"fee_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"fees\",\n" +
            "         \"id\":\"vta1q41iOwbqn\",\n" +
            "         \"attributes\":{  \n" +
            "            \"fixed\":\"0.230000\",\n" +
            "            \"percent\":\"2.104000\",\n" +
            "            \"fee_asset\":\"USD\",\n" +
            "            \"applied_to\":{  \n" +
            "               \"asset\":\"DASH\",\n" +
            "               \"subtype\":1,\n" +
            "               \"fee_type\":3,\n" +
            "               \"account_id\":\"GATXUNHVONTSYADNL3SHNFRSJDD7YQQESFTHULLBBH46O3P4CJFEGLA6\",\n" +
            "               \"account_type\":-1,\n" +
            "               \"lower_bound\":\"150.000000\",\n" +
            "               \"upper_bound\":\"500.000000\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"id\":\"GATXUNHVONTSYADNL3SHNFRSJDD7YQQESFTHULLBBH46O3P4CJFEGLA6\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"DASH\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"fee_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"USD\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val exactFeeResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"exact_fees\",\n" +
            "      \"id\":\"vta1q41iOwbqn\",\n" +
            "      \"attributes\":{  \n" +
            "         \"fixed_fee\":\"1.023212\",\n" +
            "         \"percent_fee\":\"2.943122\",\n" +
            "         \"fee_asset\":\"USD\"\n" +
            "      }\n" +
            "   }\n" +
            "}"

    val keyValueEntryResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"key_value_entries\",\n" +
            "      \"id\":\"issuance_tasks:ETH\",\n" +
            "      \"attributes\":{  \n" +
            "         \"value_type\":\"uint32\",\n" +
            "         \"value_type_i\":1,\n" +
            "         \"value\":{  \n" +
            "            \"uint32\":8,\n" +
            "            \"uint64\":null,\n" +
            "            \"string\":null\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    val keyValueEntriesListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/key_value?page=3\",\n" +
            "      \"first\":\"https://api.com/key_value?page=1\",\n" +
            "      \"prev\":\"https://api.com/key_value?page=2\",\n" +
            "      \"next\":\"https://api.com/key_value?page=4\",\n" +
            "      \"last\":\"https://api.com/key_value?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"key_value_entries\",\n" +
            "         \"id\":\"issuance_tasks:ETH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"value_type\":\"uint32\",\n" +
            "            \"value_type_i\":1,\n" +
            "            \"value\":{  \n" +
            "               \"uint32\":8,\n" +
            "               \"uint64\":null,\n" +
            "               \"string\":null\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    val offerResponseUnincluded = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"offers\",\n" +
            "      \"id\":\"10\",\n" +
            "      \"attributes\":{  \n" +
            "         \"order_book_id\":0,\n" +
            "         \"fee\":\"0.000000\",\n" +
            "         \"base_asset_code\":\"RTOKEN\",\n" +
            "         \"quote_asset_code\":\"BTC\",\n" +
            "         \"is_buy\":true,\n" +
            "         \"base_amount\":\"1.000000\",\n" +
            "         \"quote_amount\":\"9.000000\",\n" +
            "         \"price\":\"9.000000\",\n" +
            "         \"created_at\":\"2018-09-04T16:10:42Z\"\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"base_balance\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"balances\",\n" +
            "               \"id\":\"BDAS3BZ3CWUB56I2IVCEK3MUWGMLRSXPAEM634YHZWGKSGD7RNSZDIJT\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"quote_balance\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"balances\",\n" +
            "               \"id\":\"BCGYBQEWG3MTWFBHVPHOYTXXKWK6PW6IF3QACB7QW2KIUBU3UIJMAYXI\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GAULFBQKQTFHHHZEIRMYVCGTY47FKWEW7P2BY2YT45HAEODAIJUJH23T\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"base_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"RTOKEN\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"quote_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"BTC\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"
}