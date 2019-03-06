package org.tokend.sdk.api.documents

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.documents.model.DocumentUploadPolicy
import org.tokend.sdk.api.documents.model.DocumentUploadRequest
import org.tokend.sdk.api.documents.model.DocumentUrlRequest
import retrofit2.Call
import retrofit2.http.*

interface DocumentsService {
    @POST("accounts/{accountId}/documents")
    fun requestUpload(@Path("accountId") accountId: String,
                      @Body requestBody: DataEntity<DocumentUploadRequest>
    ): Call<DataEntity<AttributesEntity<DocumentUploadPolicy>>>

    @POST
    @Multipart
    @JvmSuppressWildcards
    fun upload(@Url bucketUrl: String,
               @PartMap partMap: Map<String, RequestBody>,
               @Part filePart: MultipartBody.Part): Call<Void>

    @GET("accounts/{accountId}/documents/{document}")
    fun getUrl(@Path("accountId") accountId: String,
               @Path("document") document: String): Call<DataEntity<DocumentUrlRequest>>
}