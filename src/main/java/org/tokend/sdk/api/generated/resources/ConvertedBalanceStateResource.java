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


@Type("converted-balance-states")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertedBalanceStateResource extends BaseResource {
    
    @JsonProperty("converted_amounts")
    private BalanceStateAttributeAmounts convertedAmounts;
    
    public BalanceStateAttributeAmounts getConvertedAmounts() {
        return convertedAmounts;
    }
    
    @JsonProperty("initial_amounts")
    private BalanceStateAttributeAmounts initialAmounts;
    
    public BalanceStateAttributeAmounts getInitialAmounts() {
        return initialAmounts;
    }
    
    @JsonProperty("is_converted")
    private Boolean isConverted;
    
    public Boolean isConverted() {
        return isConverted;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public boolean isFilled() {
        return             convertedAmounts != null &&
            initialAmounts != null &&
            isConverted != null &&
            price != null 
        ;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
}
