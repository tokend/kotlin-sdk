package org.tokend.sdk.api.base.model

import java.io.IOException

open class ForbiddenException(val type: String, detailMessage: String) : IOException(detailMessage)