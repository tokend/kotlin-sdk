// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResource extends BaseResource {
    
    @JsonProperty("available_for_issuance")
    private BigDecimal availableForIssuance;
    
    public BigDecimal getAvailableForIssuance() {
        return availableForIssuance;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("issued")
    private BigDecimal issued;
    
    public BigDecimal getIssued() {
        return issued;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("pending_issuance")
    private BigDecimal pendingIssuance;
    
    public BigDecimal getPendingIssuance() {
        return pendingIssuance;
    }
    
    @JsonProperty("policies")
    private Mask policies;
    
    public Mask getPolicies() {
        return policies;
    }
    
    @JsonProperty("pre_issuance_asset_signer")
    private String preIssuanceAssetSigner;
    
    public String getPreIssuanceAssetSigner() {
        return preIssuanceAssetSigner;
    }
    
    @JsonProperty("state")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum state;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getState() {
        return state;
    }
    
    @JsonProperty("trailing_digits")
    private Long trailingDigits;
    
    public Long getTrailingDigits() {
        return trailingDigits;
    }
    
    @JsonProperty("type")
    private Long type;
    
    public Long getType() {
        return type;
    }
    
    @Override
    public boolean isFilled() {
        return             availableForIssuance != null &&
            details != null &&
            issued != null &&
            maxIssuanceAmount != null &&
            pendingIssuance != null &&
            policies != null &&
            preIssuanceAssetSigner != null &&
            state != null &&
            trailingDigits != null &&
            type != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
