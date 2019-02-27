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


@Type("operations-set-fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpSetFeeDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("asset_code")
    private String assetCode;
    
    public String getAssetCode() {
        return assetCode;
    }
    
    @JsonProperty("fixed_fee")
    private BigDecimal fixedFee;
    
    public BigDecimal getFixedFee() {
        return fixedFee;
    }
    
    @JsonProperty("percent_fee")
    private BigDecimal percentFee;
    
    public BigDecimal getPercentFee() {
        return percentFee;
    }
    
    @JsonProperty("fee_type")
    private XdrEnumValue feeType;
    
    public XdrEnumValue getFeeType() {
        return feeType;
    }
    
    @JsonProperty("account_address")
    @Nullable
    private String accountAddress;
    
    @Nullable
    public String getAccountAddress() {
        return accountAddress;
    }
    
    @JsonProperty("account_role")
    @Nullable
    private Long accountRole;
    
    @Nullable
    public Long getAccountRole() {
        return accountRole;
    }
    
    @JsonProperty("subtype")
    private Long subtype;
    
    public Long getSubtype() {
        return subtype;
    }
    
    @JsonProperty("lower_bound")
    private BigDecimal lowerBound;
    
    public BigDecimal getLowerBound() {
        return lowerBound;
    }
    
    @JsonProperty("upper_bound")
    private BigDecimal upperBound;
    
    public BigDecimal getUpperBound() {
        return upperBound;
    }
    
    @JsonProperty("is_delete")
    private Boolean isDelete;
    
    public Boolean isDelete() {
        return isDelete;
    }
    
    @Override
    public boolean isFilled() {
        return             assetCode != null &&
            fixedFee != null &&
            percentFee != null &&
            feeType != null &&
            subtype != null &&
            lowerBound != null &&
            upperBound != null &&
            isDelete != null 
            && super.isFilled()
        ;
    }
}
