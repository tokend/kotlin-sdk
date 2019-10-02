// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("request-details-pre-issuance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePreIssuanceRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("signature")
    private String signature;
    
    public String getSignature() {
        return signature;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            creatorDetails != null &&
            reference != null &&
            signature != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
