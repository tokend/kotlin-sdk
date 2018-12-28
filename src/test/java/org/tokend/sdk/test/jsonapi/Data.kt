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

    val saleResponseUnincluded = "{\n" +
            "  \"data\": {\n" +
            "    \"type\": \"sales\",\n" +
            "    \"id\": \"2\",\n" +
            "    \"attributes\": {\n" +
            "      \"start_time\": \"2018-06-19T21:00:00Z\",\n" +
            "      \"end_time\": \"2018-06-29T21:00:00Z\",\n" +
            "      \"soft_cap\": \"1.000000\",\n" +
            "      \"hard_cap\": \"2.000000\",\n" +
            "      \"investors_count\": 23,\n" +
            "      \"type\": \"crowd_funding\",\n" +
            "      \"type_i\": 2,\n" +
            "      \"state\": \"closed\", \n" +
            "      \"state_i\": 2,\n" +
            "      \"base_hard_cap\": \"10.000000\",\n" +
            "      \"base_current_cap\": \"0.000000\",\n" +
            "      \"current_cap\": \"0.872974\",\n" +
            "      \"details\": {\n" +
            "         \"description\": \"FAB6BK75TFNXPRULP6YUDYBMFZUKOEYBYNZPNGRHGDMVROVEJVJQ\",\n" +
            "         \"logo\": {\n" +
            "           \"key\": \"dpurah4infpebjhcost7fvnhjxlqgqdft3bamery2an3otqpbx6jbvdt\",\n" +
            "           \"mime_type\": \"image/png\",\n" +
            "           \"name\": \"TokenD - Demo - Images (1).png\"\n" +
            "         },\n" +
            "         \"name\": \"Pre-sale\",\n" +
            "         \"short_description\": \"The best token\",\n" +
            "         \"youtube_video_id\": \"\"\n" +
            "      },\n" +
            "      \"sale_quote_assets\": [\n" +
            "        {\n" +
            "          \"asset\": \"ETH\",\n" +
            "          \"price\": \"0.000383\",\n" +
            "          \"hard_cap\": \"0.008754\",\n" +
            "          \"current_cap\": \"0.003821\",\n" +
            "          \"total_current_cap\": \"0.003822\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"balances\": {\n" +
            "        \"ETH\": \"BDZJQYMTKEHA3GDTJJSR5XPY22WHIOIRXWJMPZORZCDFD2EPDOJCROMJ\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"relationships\": {\n" +
            "      \"owner\": {\n" +
            "        \"data\": {\n" +
            "          \"type\": \"accounts\",\n" +
            "          \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "        }\n" +
            "      },\n" +
            "       \"default_quote_asset\": {\n" +
            "        \"data\": {\n" +
            "           \"type\": \"assets\",\n" +
            "           \"id\": \"USD\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"base_asset\": {\n" +
            "        \"data\": {\n" +
            "           \"type\": \"assets\",\n" +
            "           \"id\": \"QTK\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"quote_assets\": {\n" +
            "          \"data\": [\n" +
            "               {\n" +
            "                   \"type\": \"assets\",\n" +
            "                   \"id\": \"ETH\"\n" +
            "               }\n" +
            "            ]\n" +
            "       }\n" +
            "    }\n" +
            "  }\n" +
            "}"

    val salesListResponseUnincluded = "{\n" +
            "  \"meta\": {\n" +
            "    \"current_page\": 3,\n" +
            "    \"total_pages\": 10\n" +
            "  },\n" +
            "  \"links\": {\n" +
            "    \"self\": \"https://api.com/sales?page=3\",\n" +
            "    \"first\": \"https://api.com/sales?page=1\",\n" +
            "    \"prev\": \"https://api.com/sales?page=2\",\n" +
            "    \"next\": \"https://api.com/sales?page=4\",\n" +
            "    \"last\": \"https://api.com/sales?page=15\"\n" +
            "  },\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"type\": \"sales\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"attributes\": {\n" +
            "        \"start_time\": \"2018-06-19T21:00:00Z\",\n" +
            "        \"end_time\": \"2018-06-29T21:00:00Z\",\n" +
            "        \"soft_cap\": \"1.000000\",\n" +
            "        \"hard_cap\": \"2.000000\",\n" +
            "        \"investors_count\": 23,\n" +
            "        \"type\": \"crowd_funding\",\n" +
            "        \"type_i\": 2,\n" +
            "        \"state\": \"closed\",\n" +
            "        \"state_i\": 2,\n" +
            "        \"base_hard_cap\": \"10.000000\",\n" +
            "        \"base_current_cap\": \"0.000000\",\n" +
            "        \"current_cap\": \"0.872974\",\n" +
            "        \"details\": {\n" +
            "           \"description\": \"FAB6BK75TFNXPRULP6YUDYBMFZUKOEYBYNZPNGRHGDMVROVEJVJQ\",\n" +
            "           \"logo\": {\n" +
            "             \"key\": \"dpurah4infpebjhcost7fvnhjxlqgqdft3bamery2an3otqpbx6jbvdt\",\n" +
            "             \"mime_type\": \"image/png\",\n" +
            "             \"name\": \"TokenD - Demo - Images (1).png\"\n" +
            "           },\n" +
            "           \"name\": \"Pre-sale\",\n" +
            "           \"short_description\": \"The best token\",\n" +
            "           \"youtube_video_id\": \"\"\n" +
            "        },\n" +
            "        \"sale_quote_assets\": [\n" +
            "          {\n" +
            "            \"asset\": \"ETH\",\n" +
            "            \"price\": \"0.000383\",\n" +
            "            \"hard_cap\": \"0.008754\",\n" +
            "            \"current_cap\": \"0.003821\",\n" +
            "            \"total_current_cap\": \"0.003822\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"balances\": {\n" +
            "          \"ETH\": \"BDZJQYMTKEHA3GDTJJSR5XPY22WHIOIRXWJMPZORZCDFD2EPDOJCROMJ\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"relationships\": {\n" +
            "        \"owner\": {\n" +
            "          \"data\": {\n" +
            "            \"type\": \"accounts\",\n" +
            "            \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "          }\n" +
            "        },\n" +
            "       \"default_quote_asset\": {\n" +
            "           \"data\": {\n" +
            "               \"type\": \"assets\",\n" +
            "               \"id\": \"USD\"\n" +
            "           }\n" +
            "       },\n" +
            "       \"base_asset\": {\n" +
            "           \"data\": {\n" +
            "               \"type\": \"assets\",\n" +
            "               \"id\": \"QTK\"\n" +
            "           }\n" +
            "      },\n" +
            "      \"quote_assets\": {\n" +
            "          \"data\": [\n" +
            "               {\n" +
            "                   \"type\": \"assets\",\n" +
            "                   \"id\": \"ETH\"\n" +
            "               }\n" +
            "            ]\n" +
            "       }\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val transactionResponse = "{\n" +
            "  \"data\": {\n" +
            "    \"type\": \"transactions\",\n" +
            "    \"id\": \"b21064dfcc521fe7f7e56d00630aa2675c9b3f5b1a8dd92f74b3c52062d5adb4\",\n" +
            "    \"attributes\": {\n" +
            "      \"paging_token\": \"73014452224\",\n" +
            "      \"hash\": \"b21064dfcc521fe7f7e56d00630aa2675c9b3f5b1a8dd92f74b3c52062d5adb4\",\n" +
            "      \"ledger\": 17,\n" +
            "      \"created_at\": \"2018-04-07T17:40:16Z\",\n" +
            "      \"source_account\": \"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "      \"fee_paid\": 0,\n" +
            "      \"operation_count\": 1,\n" +
            "      \"envelope_xdr\": \"AAAAAP4DpOIcoI8urCJITRZtEDS0wzyPuGojb7AbKpHcMR1gAAAAAAAAAAAAAAAAAAAAAAAAAABa0i7rAAAAAAAAAAEAAAAAAAAADwAAAAAAAAADRVRIAAAAAANTVU4AAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdwxHWAAAABAM8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\",\n" +
            "      \"result_xdr\": \"AAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAPAAAAAAAAAABJPYrQAAAAAAAAAAA=\",\n" +
            "      \"result_meta_xdr\": \"AAAAAAAAAAEAAAABAAAAAAAAABEAAAAMAAAAA0VUSAAAAAADU1VOAAAAAABJPYrQAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\n" +
            "      \"fee_meta_xdr\": \"AAAAAA==\",\n" +
            "      \"memo_type\": \"none\",\n" +
            "      \"signatures\": [\n" +
            "        \"M8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\"\n" +
            "      ],\n" +
            "      \"valid_after\": \"1970-01-01T00:00:00Z\",\n" +
            "      \"valid_before\": \"2018-04-14T16:40:12Z\"\n" +
            "    }\n" +
            "  }\n" +
            "}"

    val transactionsListResponse = "{\n" +
            "  \"meta\": {\n" +
            "    \"total_pages\": 10,\n" +
            "    \"latest_ledger\": {\n" +
            "      \"sequence\": 19,\n" +
            "      \"closed_at\": \"2018-04-07T17:40:26Z\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"links\": {\n" +
            "    \"self\": \"https://api.com/transactions?page=3\",\n" +
            "    \"first\": \"https://api.com/transactions?page=1\",\n" +
            "    \"prev\": \"https://api.com/transactions?page=2\",\n" +
            "    \"next\": \"https://api.com/transactions?page=4\",\n" +
            "    \"last\": \"https://api.com/transactions?page=13\"\n" +
            "  },\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"type\": \"transactions\",\n" +
            "      \"id\": \"d3275938107d7dd585ef44b717e67597e0c736591b2901708c90162ea94e4e0c\",\n" +
            "      \"attributes\": {\n" +
            "        \"paging_token\": \"73014452224\",\n" +
            "        \"hash\": \"d3275938107d7dd585ef44b717e67597e0c736591b2901708c90162ea94e4e0c\",\n" +
            "        \"ledger\": 16,\n" +
            "        \"created_at\": \"2018-04-07T17:40:11Z\",\n" +
            "        \"source_account\": \"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "        \"fee_paid\": 0,\n" +
            "        \"operation_count\": 1,\n" +
            "        \"envelope_xdr\": \"AAAAAP4DpOIcoI8urCJITRZtEDS0wzyPuGojb7Ab....\",\n" +
            "        \"result_xdr\": \"AAAAAAAAAAAAAAAAAAAAAQAAAAAAAAALAAAAAAAAAAAA...\",\n" +
            "        \"result_meta_xdr\": \"AAAAAAAAAAEAAAAGAAAAAAAAABAAAAAPAAAAAAAA...\",\n" +
            "        \"fee_meta_xdr\": \"AAAAAA==\",\n" +
            "        \"memo_type\": \"none\",\n" +
            "        \"signatures\": [\n" +
            "          \"Pk2+1r5puXM5OVGkRoYke6S+78xTFVT0YWVlknHWFpK1IqpYBvaHzJQWiLftJWgH/J5hLfyHoYFjaNKrl6dyCA==\"\n" +
            "        ],\n" +
            "        \"valid_after\": \"1970-01-01T00:00:00Z\",\n" +
            "        \"valid_before\": \"2018-04-14T16:40:07Z\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"

    val requestAssetCreateResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"212\",\n" +
            "      \"type\":\"requests\",\n" +
            "      \"attributes\":{  \n" +
            "         \"created_at\":\"2018-04-07T17:40:11Z\",\n" +
            "         \"state\":\"approved\",\n" +
            "         \"state_i\":3,\n" +
            "         \"hash\":\"2b973d51a10646505745aa7f1c860a383ea7361dac1b41779cc079dc385870dc\",\n" +
            "         \"reject_reason\":\"\",\n" +
            "         \"reference\":\"BTC\",\n" +
            "         \"updated_at\":\"2018-04-07T17:40:11Z\"\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"requestor\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"reviewer\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"request_details\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"asset_create_request_details\",\n" +
            "               \"id\":\"212\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"asset_create_request_details\",\n" +
            "         \"id\":\"212\",\n" +
            "         \"attributes\":{  \n" +
            "            \"code\":\"BTC\",\n" +
            "            \"policies\":[  \n" +
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
            "            \"pre_issued_asset_signer\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "            \"max_issuance_amount\":\"9223372036854.775807\",\n" +
            "            \"initial_preissued_amount\":\"0.000000\",\n" +
            "            \"details\":{  \n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"type\":\"logo_type\",\n" +
            "                  \"url\":\"logo_url\"\n" +
            "               },\n" +
            "               \"name\":\"BTC name\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}