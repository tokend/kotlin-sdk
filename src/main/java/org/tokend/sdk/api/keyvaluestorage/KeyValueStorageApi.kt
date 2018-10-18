package org.tokend.sdk.api.keyvaluestorage

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.keyvaluestorage.model.KeyEntry

class KeyValueStorageApi(
        protected val keyService: KeyValueStorageService
) {

    /**
     * Will return list of key-value entries.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-key-value-entries">Docs</a>
     */
    fun getEntries(): ApiRequest<List<KeyEntry>> {
        return SimpleRetrofitApiRequest(
                keyService.getKeyValueEntries()
        )
    }

    /**
     * Will return an entry with the specified key.
     * @see <a href="https://tokend.gitlab.io/docs/?http#get-key-value-entry-by-key">Docs</a>
     */
    fun getEntryByKey(key: String): ApiRequest<KeyEntry> {
        return SimpleRetrofitApiRequest(
                keyService.getKeyValueEntryByKey(key)
        )
    }
}