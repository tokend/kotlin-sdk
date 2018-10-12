package org.tokend.sdk.api.tfa

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.requests.AttributesEntity
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.requests.models.CreateTfaRequestBody
import org.tokend.sdk.api.tfa.model.TfaFactor

open class TfaApi(
        protected val tfaService: TfaService
) {
    open fun getFactors(walletId: String): ApiRequest<List<TfaFactor>> {
        return MappedRetrofitApiRequest(
                tfaService.getFactors(walletId),
                { it.data }
        )
    }

    open fun createFactor(walletId: String,
                          type: TfaFactor.Type): ApiRequest<TfaFactor> {
        return MappedRetrofitApiRequest(
                tfaService.createFactor(
                        walletId,
                        DataEntity(
                                CreateTfaRequestBody(type.literal)
                        )
                ),
                { it.data }
        )
    }

    open fun updateFactor(walletId: String,
                          factorId: Long,
                          attributes: TfaFactor.Attributes): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                tfaService.updateFactor(
                        walletId,
                        factorId,
                        DataEntity(
                                AttributesEntity(attributes)
                        )
                )
        )
    }

    open fun deleteFactor(walletId: String,
                          factorId: Long): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                tfaService.deleteFactor(walletId, factorId)
        )
    }
}