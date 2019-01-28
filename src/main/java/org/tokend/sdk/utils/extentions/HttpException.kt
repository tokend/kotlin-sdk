@file:JvmName("HttpExceptionUtil")

package org.tokend.sdk.utils.extentions

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.jasminb.jsonapi.ErrorUtils
import com.github.jasminb.jsonapi.models.errors.Error
import com.github.jasminb.jsonapi.models.errors.Errors
import org.tokend.sdk.factory.JsonApiFactory
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

fun HttpException.isNotFound(): Boolean = codeIs(HttpURLConnection.HTTP_NOT_FOUND)

fun HttpException.isForbidden(): Boolean = codeIs(HttpURLConnection.HTTP_FORBIDDEN)

fun HttpException.isBadRequest(): Boolean = codeIs(HttpURLConnection.HTTP_BAD_REQUEST)

fun HttpException.isUnauthorized(): Boolean = codeIs(HttpURLConnection.HTTP_UNAUTHORIZED)

fun HttpException.isConflict(): Boolean = codeIs(HttpURLConnection.HTTP_CONFLICT)

fun HttpException.isServerError(): Boolean = code() in 500..599

/**
 * @return true if HTTP error code matches given [httpCode]
 *
 * @see HttpURLConnection
 */
fun HttpException.codeIs(httpCode: Int): Boolean =
        code() == httpCode

/**
 * Parses JSONAPI errors from the response error body and closes it
 *
 * @return list of parsed errors
 *
 * @throws IOException if response error body stream is not available
 */
@JvmOverloads
@Throws(IOException::class)
fun HttpException.getErrors(mapper: ObjectMapper = JsonApiFactory().getObjectMapper()): List<Error> {
    return response().errorBody().use { errorBody ->
        val stream = errorBody.byteStream()

        if (stream.available() <= 0) {
            throw IOException("Response error body stream is not available")
        }

        ErrorUtils
                .parseError(
                        mapper,
                        stream,
                        Errors::class.java)
                .errors
    }
}
