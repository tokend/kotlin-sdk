package org.tokend.sdk.api.v3.keyvalue

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.generated.resources.KeyValueEntryResource

open class KeyValueStorageApiV3(
        protected open val keyValueStorageService: KeyValueStorageServiceV3
) {
    /**
     * @return key-value entries list page
     */
    open fun get(): ApiRequest<DataPage<KeyValueEntryResource>> {
        return MappedRetrofitApiRequest(
                keyValueStorageService.getEntries(emptyMap()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return key-value entry by it's ID
     */
    open fun getById(id: String): ApiRequest<KeyValueEntryResource> {
        return MappedRetrofitApiRequest(
                keyValueStorageService.getEntryById(
                        id,
                        emptyMap()
                ),
                JSONAPIDocument<KeyValueEntryResource>::get
        )
    }
}