package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.resources.OperationDetailsResource
import org.tokend.sdk.api.generated.resources.OperationResource

/**
 * @return the result of an unchecked cast of [OperationResource.details] to [T]
 */
fun <T : OperationDetailsResource> OperationResource.getTypedDetails(): T {
    return details as T
}