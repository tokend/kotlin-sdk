package org.tokend.sdk.api.integrations.escrow.params

import java.math.BigDecimal

class CreateEscrowParams(
        val paymentMethodId: String,
        val amount: BigDecimal,
        val asset: String,
        val sourceAccount: String,
        val sourceEmail: String,
        val destAccount: String,
        val destEmail: String?,
        val subject: String?
)