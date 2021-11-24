package org.tokend.sdk.api.tfa

import com.fasterxml.jackson.module.kotlin.treeToValue
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.api.tfa.model.TfaFactorCreationResult
import org.tokend.sdk.factory.JsonApiToolsProvider

open class TfaApi(
    protected val tfaService: TfaService
) {
    /**
     * Returns list of factors used for 2Factor Auth.
     * @see <a href="https://tokend.gitlab.io/docs/#get-factors">Docs</a>
     */
    open fun getFactors(walletId: String): ApiRequest<List<TfaFactor>> {
        return MappedRetrofitApiRequest(
            tfaService.getFactors(walletId),
            { it.data }
        )
    }

    /**
     * Creates non-active TFA factor of given type.
     * @see <a href="https://tokend.gitlab.io/docs/#create-factor">Docs</a>
     */
    open fun createFactor(
        walletId: String,
        type: TfaFactor.Type
    ): ApiRequest<TfaFactorCreationResult> {
        return MappedRetrofitApiRequest(
            tfaService.createFactor(
                walletId,
                DataEntity(
                    mapOf(
                        "type" to type.name.toLowerCase()
                    )
                )
            ),
            {
                val json = it.data

                val id = json["id"].asLong()
                val factor = TfaFactor(id, type, TfaFactor.Attributes(0))

                val attributesElement = json["attributes"]

                val mapper = JsonApiToolsProvider.getObjectMapper()
                val attributes: Map<String, Any> =
                    if (attributesElement != null)
                        mapper.treeToValue<Map<String, Any>>(attributesElement)!!
                    else emptyMap()

                TfaFactorCreationResult(factor, attributes)
            }
        )
    }

    /**
     * Updates given TFA factor.
     * @see <a href="https://tokend.gitlab.io/docs/#update-factor">Docs</a>
     */
    open fun updateFactor(
        walletId: String,
        factorId: Long,
        attributes: TfaFactor.Attributes
    ): ApiRequest<Void> {
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

    /**
     * Deletes given TFA factor.
     * @see <a href="https://tokend.gitlab.io/docs/#delete-factor">Docs</a>
     */
    open fun deleteFactor(
        walletId: String,
        factorId: Long
    ): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
            tfaService.deleteFactor(walletId, factorId)
        )
    }
}