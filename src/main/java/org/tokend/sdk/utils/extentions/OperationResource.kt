package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.v3.model.generated.resources.BaseOperationDetailsResource
import org.tokend.sdk.api.v3.model.generated.resources.OperationResource

/**
 * @return the result of an unchecked cast of [OperationResource.details] to [T]
 */
fun <T : BaseOperationDetailsResource> OperationResource.getTypedDetails(): T {
    return details as T
}