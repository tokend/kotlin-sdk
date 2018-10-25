package org.tokend.sdk.api.documents

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.documents.model.DocumentType
import org.tokend.sdk.api.documents.model.DocumentUploadPolicy
import org.tokend.sdk.api.documents.model.DocumentUploadRequest
import java.io.File

open class DocumentsApi(
        protected val documentsService: DocumentsService
) {
    /**
     * Will return document upload policy map.
     *
     * @param contentType must be an allowed MIME type, see allowed types in Docs.
     *
     * @see <a href="https://tokend.gitlab.io/docs/?http#upload">Docs</a>
     */
    open fun requestUpload(accountId: String,
                           documentType: DocumentType,
                           contentType: String): ApiRequest<DocumentUploadPolicy> {
        return requestUpload(accountId, documentType.literal, contentType)
    }

    /**
     * Will return document upload policy map.
     *
     * @param documentType must be an allowed document type, see allowed types in Docs.
     * @param contentType must be an allowed MIME type, see allowed types in Docs.
     *
     * @see <a href="https://tokend.gitlab.io/docs/?http#upload">Docs</a>
     */
    open fun requestUpload(accountId: String,
                           documentType: String,
                           contentType: String): ApiRequest<DocumentUploadPolicy> {
        return MappedRetrofitApiRequest(
                documentsService.requestUpload(
                        accountId,
                        DataEntity(
                                DocumentUploadRequest(documentType, contentType)
                        )
                ),
                { it.data.attributes }
        )
    }

    /**
     * Uploads given file according to the policy.
     *
     * @see requestUpload
     */
    open fun upload(policy: DocumentUploadPolicy,
                    file: File,
                    contentType: String): ApiRequest<Void> {
        val filePart = MultipartBody.Part.createFormData("file", file.name,
                RequestBody.create(MediaType.parse(contentType), file))

        return uploadFileMultipart(policy, filePart)
    }

    /**
     * Uploads given file content according to the policy.
     *
     * @see requestUpload
     */
    open fun upload(policy: DocumentUploadPolicy,
                    contentType: String,
                    fileName: String,
                    content: ByteArray): ApiRequest<Void> {
        val filePart = MultipartBody.Part.createFormData("file", fileName,
                RequestBody.create(MediaType.parse(contentType), content))

        return uploadFileMultipart(policy, filePart)
    }

    protected open fun uploadFileMultipart(policy: DocumentUploadPolicy,
                                           filePart: MultipartBody.Part): ApiRequest<Void> {
        val policyMultipart = policy.toMutableMap()
                .also {
                    it.remove(POLICY_URL_KEY)
                }
                .mapValues {
                    RequestBody.create(MediaType.parse("text/plain"), it.value)
                }

        val uploadUrl = policy[POLICY_URL_KEY]
                ?: throw IllegalArgumentException("Missing '$POLICY_URL_KEY' from policy")

        return SimpleRetrofitApiRequest(
                documentsService.upload(
                        uploadUrl,
                        policyMultipart,
                        filePart
                )
        )
    }

    companion object {
        private const val POLICY_URL_KEY = "url"
    }
}