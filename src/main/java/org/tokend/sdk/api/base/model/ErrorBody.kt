package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import okhttp3.ResponseBody
import org.tokend.sdk.factory.JsonApiToolsProvider
import retrofit2.HttpException
import retrofit2.Response

/**
 * Holds errors returned from server.
 */
class ErrorBody(
    @JsonProperty("errors")
    val errors: List<ServerError>
) {
    /**
     * Returns first error or [null] if there are no errors somehow.
     */
    val firstOrNull: ServerError?
        get() = errors.firstOrNull()

    companion object {
        @JvmStatic
        fun fromJsonString(json: String): ErrorBody {
            return JsonApiToolsProvider.getObjectMapper().readValue(json, ErrorBody::class.java)
        }

        /**
         * Parses [ErrorBody] from [Response.errorBody].
         * Closes [ResponseBody] automatically.
         */
        @JvmStatic
        fun fromRetrofitResponse(response: Response<*>): ErrorBody {
            return fromJsonString(response.errorBody().string())
        }

        /**
         * Parses [ErrorBody] from [HttpException.response]
         * Closes [ResponseBody] automatically.
         */
        @JvmStatic
        fun fromHttpException(httpException: HttpException): ErrorBody {
            return fromRetrofitResponse(httpException.response())
        }
    }
}