package org.tokend.sdk.api.v3.signers

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.SignerResource
import org.tokend.sdk.api.v3.model.generated.resources.SignerRoleResource
import org.tokend.sdk.api.v3.model.generated.resources.SignerRuleResource
import org.tokend.sdk.api.v3.signers.params.SignerParamsV3
import org.tokend.sdk.api.v3.signers.params.SignerRoleParamsV3
import org.tokend.sdk.api.v3.signers.params.SignerRolesPageParamsV3
import org.tokend.sdk.api.v3.signers.params.SignerRulesPageParamsV3

open class SignersApiV3(
     protected open val signersService: SignersServiceV3
) {

    /**
     * @return signers list of provided account id.
     */
    open fun get(accountId: String, params: SignerParamsV3? = null): ApiRequest<List<SignerResource>> {
        return MappedRetrofitApiRequest(
                signersService.getAccountSigners(accountId, params.map()),
                JSONAPIDocument<List<SignerResource>>::get
        )
    }

    /**
     * @return signers roles page.
     */
    open fun getRoles(params: SignerRolesPageParamsV3? = null): ApiRequest<DataPage<SignerRoleResource>> {
        return MappedRetrofitApiRequest(
                signersService.getSignerRoles(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return role by it's id.
     */
    open fun getRoleById(id: String, params: SignerRoleParamsV3? = null): ApiRequest<SignerRoleResource> {
        return MappedRetrofitApiRequest(
                signersService.getSignerRoleById(id, params.map()),
                JSONAPIDocument<SignerRoleResource>::get
        )
    }

    /**
     * @return signers rules page.
     */
    open fun getRules(params: SignerRulesPageParamsV3? = null): ApiRequest<DataPage<SignerRuleResource>> {
        return MappedRetrofitApiRequest(
                signersService.getSignerRules(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return rule by it's id.
     */
    open fun getRuleById(id: String, params: SignerParamsV3? = null): ApiRequest<SignerRuleResource> {
        return MappedRetrofitApiRequest(
                signersService.getSignerRuleById(id, params.map()),
                JSONAPIDocument<SignerRuleResource>::get
        )
    }
}