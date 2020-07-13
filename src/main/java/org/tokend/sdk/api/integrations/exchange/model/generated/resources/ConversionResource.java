// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.exchange.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("conversion-results")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversionResource extends BaseResource {
    
    @JsonProperty("transaction_envelope")
    private String transactionEnvelope;
    
    public String getTransactionEnvelope() {
        return transactionEnvelope;
    }
    
    @Override
    public boolean isFilled() {
        return             transactionEnvelope != null 
        ;
    }
}
