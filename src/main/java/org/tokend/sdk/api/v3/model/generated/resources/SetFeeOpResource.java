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


@Type("operations-set-fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SetFeeOpResource extends BaseOperationDetailsResource {
    
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
    
    @JsonProperty("asset_code")
    private String assetCode;
    
    public String getAssetCode() {
        return assetCode;
    }
    
    @JsonProperty("fee_type")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum feeType;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getFeeType() {
        return feeType;
    }
    
    @JsonProperty("fixed_fee")
    private BigDecimal fixedFee;
    
    public BigDecimal getFixedFee() {
        return fixedFee;
    }
    
    @JsonProperty("is_delete")
    private Boolean isDelete;
    
    public Boolean isDelete() {
        return isDelete;
    }
    
    @JsonProperty("lower_bound")
    private BigDecimal lowerBound;
    
    public BigDecimal getLowerBound() {
        return lowerBound;
    }
    
    @JsonProperty("percent_fee")
    private BigDecimal percentFee;
    
    public BigDecimal getPercentFee() {
        return percentFee;
    }
    
    @JsonProperty("subtype")
    private Long subtype;
    
    public Long getSubtype() {
        return subtype;
    }
    
    @JsonProperty("upper_bound")
    private BigDecimal upperBound;
    
    public BigDecimal getUpperBound() {
        return upperBound;
    }
    
    @Override
    public boolean isFilled() {
        return             assetCode != null &&
            feeType != null &&
            fixedFee != null &&
            isDelete != null &&
            lowerBound != null &&
            percentFee != null &&
            subtype != null &&
            upperBound != null 
            && super.isFilled()
        ;
    }
}
