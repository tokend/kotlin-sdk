// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("request-details-issuance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuanceRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null &&
            externalDetails != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("receiver")
    private BalanceResource receiver;
    
    public BalanceResource getReceiver() {
        return receiver;
    }
}
