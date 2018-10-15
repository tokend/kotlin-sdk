package org.tokend.sdk.api.users

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.users.model.CreateUserRequestBody
import org.tokend.sdk.api.users.model.SimpleUser
import org.tokend.sdk.api.users.model.UserAttributes

open class UsersApi(
        protected val usersService: UsersService
) {
    open fun get(accountId: String): ApiRequest<SimpleUser> {
        return MappedRetrofitApiRequest(
                usersService.getUserInfo(accountId),
                { it.data!! }
        )
    }

    open fun create(accountId: String,
                    userType: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                usersService.createUser(
                        accountId,
                        DataEntity(
                                AttributesEntity(
                                        CreateUserRequestBody(userType)
                                )
                        )
                )
        )
    }

    open fun patch(accountId: String,
                   attributes: UserAttributes): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                usersService.patchUser(
                        accountId,
                        DataEntity(
                                AttributesEntity(attributes)
                        )
                )
        )
    }
}