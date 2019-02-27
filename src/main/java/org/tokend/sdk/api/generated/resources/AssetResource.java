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


@Type("assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResource extends BaseResource {
    
    @JsonProperty("pre_issuance_asset_signer")
    private String preIssuanceAssetSigner;
    
    public String getPreIssuanceAssetSigner() {
        return preIssuanceAssetSigner;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("available_for_issuance")
    private BigDecimal availableForIssuance;
    
    public BigDecimal getAvailableForIssuance() {
        return availableForIssuance;
    }
    
    @JsonProperty("issued")
    private BigDecimal issued;
    
    public BigDecimal getIssued() {
        return issued;
    }
    
    @JsonProperty("pending_issuance")
    private BigDecimal pendingIssuance;
    
    public BigDecimal getPendingIssuance() {
        return pendingIssuance;
    }
    
    @JsonProperty("policies")
    private XdrEnumBitmask policies;
    
    public XdrEnumBitmask getPolicies() {
        return policies;
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
        return             preIssuanceAssetSigner != null &&
            details != null &&
            maxIssuanceAmount != null &&
            availableForIssuance != null &&
            issued != null &&
            pendingIssuance != null &&
            policies != null &&
            trailingDigits != null &&
            type != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
