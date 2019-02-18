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


@Type("fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeResource extends BaseResource {
    
    @JsonProperty("fixed")
    private BigDecimal fixed;
    
    public BigDecimal getFixed() {
        return fixed;
    }
    
    @JsonProperty("percent")
    private BigDecimal percent;
    
    public BigDecimal getPercent() {
        return percent;
    }
    
    @JsonProperty("applied_to")
    private FeeAppliedTo appliedTo;
    
    public FeeAppliedTo getAppliedTo() {
        return appliedTo;
    }
    
    @Override
    public boolean hasAttributes() {
        return             fixed != null &&
            percent != null &&
            appliedTo != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @Relationship("account_role")
    private RoleResource accountRole;
    
    public RoleResource getAccountRole() {
        return accountRole;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
