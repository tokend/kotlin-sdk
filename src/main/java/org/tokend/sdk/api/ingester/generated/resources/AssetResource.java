// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResource extends BaseResource {
    
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
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @JsonProperty("state")
    private Long state;
    
    public Long getState() {
        return state;
    }
    
    @JsonProperty("trailing_digits")
    private Long trailingDigits;
    
    public Long getTrailingDigits() {
        return trailingDigits;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            issued != null &&
            maxIssuanceAmount != null &&
            pendingIssuance != null &&
            securityType != null &&
            state != null &&
            trailingDigits != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
