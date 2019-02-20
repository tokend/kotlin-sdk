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


@Type("request-details-pre-issuance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreIssuanceRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("signature")
    private String signature;
    
    public String getSignature() {
        return signature;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null &&
            signature != null &&
            reference != null &&
            creatorDetails != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
