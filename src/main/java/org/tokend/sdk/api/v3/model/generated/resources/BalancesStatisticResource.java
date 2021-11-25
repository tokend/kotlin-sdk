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


@Type("balances-statistic")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalancesStatisticResource extends BaseResource {
    
    @JsonProperty("asset")
    private String asset;
    
    public String getAsset() {
        return asset;
    }
    
    @JsonProperty("available_amount")
    private BigDecimal availableAmount;
    
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }
    
    @JsonProperty("closed_sales_amount")
    private BigDecimal closedSalesAmount;
    
    public BigDecimal getClosedSalesAmount() {
        return closedSalesAmount;
    }
    
    @JsonProperty("full_amount")
    private BigDecimal fullAmount;
    
    public BigDecimal getFullAmount() {
        return fullAmount;
    }
    
    @JsonProperty("pending_sales_amount")
    private BigDecimal pendingSalesAmount;
    
    public BigDecimal getPendingSalesAmount() {
        return pendingSalesAmount;
    }
    
    @Override
    public boolean isFilled() {
        return             asset != null &&
            availableAmount != null &&
            closedSalesAmount != null &&
            fullAmount != null &&
            pendingSalesAmount != null 
        ;
    }
}
