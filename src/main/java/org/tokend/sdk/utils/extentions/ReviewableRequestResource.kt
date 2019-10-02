package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.resources.BaseReviewableRequestDetailsResource
import org.tokend.sdk.api.generated.resources.ReviewableRequestResource

/**
 * @return the result of an unchecked cast of [ReviewableRequestResource.requestDetails] to [T]
 */
fun <T : BaseReviewableRequestDetailsResource> ReviewableRequestResource.getTypedRequestDetails(): T {
    return this.requestDetails as T
}