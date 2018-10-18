package org.tokend.sdk.api.keyvaluestorage

import org.tokend.sdk.api.keyvaluestorage.model.KeyEntry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface KeyValueStorageService {
    @GET("key_value")
    fun getKeyValueEntries(): Call<List<KeyEntry>>

    @GET("key_value/{entryKey}")
    fun getKeyValueEntryByKey(@Path("entryKey") key: String): Call<KeyEntry>
}