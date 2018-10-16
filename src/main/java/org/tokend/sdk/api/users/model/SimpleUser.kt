package org.tokend.sdk.api.users.model

class SimpleUser(type: String, id: String, attributes: UserAttributes)
    : User<UserAttributes>(type, id, attributes)