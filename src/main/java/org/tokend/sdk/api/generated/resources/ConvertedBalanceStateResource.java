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


@Type("converted-balance-states")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertedBalanceStateResource extends BaseResource {
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("initial_amounts")
    private BalanceStateAmounts initialAmounts;
    
    public BalanceStateAmounts getInitialAmounts() {
        return initialAmounts;
    }
    
    @JsonProperty("converted_amounts")
    private BalanceStateAmounts convertedAmounts;
    
    public BalanceStateAmounts getConvertedAmounts() {
        return convertedAmounts;
    }
    
    @JsonProperty("is_converted")
    private Boolean isConverted;
    
    public Boolean isConverted() {
        return isConverted;
    }
    
    @Override
    public boolean isFilled() {
        return             price != null &&
            initialAmounts != null &&
            convertedAmounts != null &&
            isConverted != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
}
