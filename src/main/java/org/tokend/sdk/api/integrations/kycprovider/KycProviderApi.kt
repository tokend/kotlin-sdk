package org.tokend.sdk.api.integrations.kycprovider

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.kycprovider.model.generated.resources.KycResource
import org.tokend.sdk.factory.JsonApiTools

open class KycProviderApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun getByAccountId(accountId: String): ApiRequest<KycResource> =
            customRequestsApi.get(
                    url = "integrations/kyc/$accountId",
                    responseClass = KycResource::class.java
            )

    open fun getByAccountIds(accountIds: Set<String>): ApiRequest<Collection<KycResource>> =
            customRequestsApi.post(
                    url = "integrations/kyc",
                    body = DataEntity(mapOf(
                            "relationships" to mapOf(
                                    "accounts" to DataEntity(accountIds.map { mapOf("id" to it) })
                            )
                    )),
                    responseClass = ByteArray::class.java
            )
                    .map { rawBytes ->
                        JsonApiTools.getResourceConverter()
                                .readDocumentCollection(rawBytes, KycResource::class.java)
                                .get()
                    }
}