package org.tokend.sdk.api.integrations.escrow

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.escrow.model.CreatedEscrowAttributes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EscrowService {
    @POST("integrations/escrow")
    @JvmSuppressWildcards
    fun createEscrow(@Body body: DataEntity<Map<String, Any>>): Call<DataEntity<AttributesEntity<CreatedEscrowAttributes>>>
}