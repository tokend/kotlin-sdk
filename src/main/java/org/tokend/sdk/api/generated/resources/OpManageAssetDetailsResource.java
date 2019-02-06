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


@Type("operations-manage-asset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageAssetDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("asset_code")
    private String assetCode;
    
    public String getAssetCode() {
        return assetCode;
    }
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
        return action;
    }
    
    @JsonProperty("policies")
    @Nullable
    private XdrEnumBitmask policies;
    
    @Nullable
    public XdrEnumBitmask getPolicies() {
        return policies;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("preissuance_signer")
    private String preissuanceSigner;
    
    public String getPreissuanceSigner() {
        return preissuanceSigner;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @Override
    public boolean hasAttributes() {
        return             assetCode != null &&
            action != null &&
            details != null &&
            preissuanceSigner != null &&
            maxIssuanceAmount != null 
        ;
    }
}
