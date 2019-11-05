package org.tokend.sdk.api.identity

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.model.MassEmailAccountKey
import org.tokend.sdk.api.identity.model.SetPhoneRequestAttributes
import org.tokend.sdk.api.identity.model.SetTelegramRequestAttributes
import org.tokend.sdk.api.identity.params.IdentitiesPageParams
import org.tokend.sdk.api.identity.model.IdentitySettingsResource

open class IdentitiesApi(
        protected open val identitesService: IdentitiesService
) {
    /**
     * @return identities list page
     */
    @JvmOverloads
    open fun get(params: IdentitiesPageParams? = null): ApiRequest<DataPage<IdentityResource>> {
        return MappedRetrofitApiRequest(
                identitesService.get(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return identities for given accounts
     */
    open fun getForAccounts(vararg accountIds: String): ApiRequest<List<IdentityResource>> {
        return getForAccounts(accountIds.toList())
    }

    /**
     * @return identities for given accounts
     */
    open fun getForAccounts(accountIds: List<String>): ApiRequest<List<IdentityResource>> {
        return MappedRetrofitApiRequest(
                identitesService.getByAccountIds(
                        DataEntity(accountIds.map(::MassEmailAccountKey))
                ),
                JSONAPIDocument<List<IdentityResource>>::get
        )
    }

    open fun setPhoneNumber(accountId: String,
                            phoneNumber: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                identitesService.setPhone(
                        accountId,
                        DataEntity(AttributesEntity(SetPhoneRequestAttributes(phoneNumber)))
                )
        )
    }

    open fun setTelegramUsername(accountId: String,
                                 username: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                identitesService.setTelegramUsername(
                        accountId,
                        DataEntity(AttributesEntity(SetTelegramRequestAttributes(username)))
                )
        )
    }

    @JvmOverloads
    open fun getSettings(accountId: String,
                         pagingParams: PagingParamsV2? = null): ApiRequest<DataPage<IdentitySettingsResource>> {
        return MappedRetrofitApiRequest(
                identitesService.getSettings(
                        accountId,
                        pagingParams.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getSettingsItemByKey(accountId: String,
                                  key: String): ApiRequest<IdentitySettingsResource> {
        return MappedRetrofitApiRequest(
                identitesService.getSettingsItemByKey(accountId, key),
                JSONAPIDocument<IdentitySettingsResource>::get
        )
    }


    /**
     * @param value - will be serialzied with Gson
     */
    open fun setSettingsItem(accountId: String,
                             key: String,
                             value: Any): ApiRequest<Void> {
        val data = DataEntity(AttributesEntity(mapOf(
                "key" to key,
                "value" to value
        )))

        return SimpleRetrofitApiRequest(
                identitesService.setSettingsItem(accountId, data)
        )
    }
}