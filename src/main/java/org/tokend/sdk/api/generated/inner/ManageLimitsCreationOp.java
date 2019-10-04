// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageLimitsCreationOp {
    
    @JsonProperty("account_address")
    private String accountAddress;
    
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
    
    @JsonProperty("annual_out")
    private BigDecimal annualOut;
    
    public BigDecimal getAnnualOut() {
        return annualOut;
    }
    
    @JsonProperty("asset_code")
    private String assetCode;
    
    public String getAssetCode() {
        return assetCode;
    }
    
    @JsonProperty("daily_out")
    private BigDecimal dailyOut;
    
    public BigDecimal getDailyOut() {
        return dailyOut;
    }
    
    @JsonProperty("is_convert_needed")
    private Boolean isConvertNeeded;
    
    public Boolean isConvertNeeded() {
        return isConvertNeeded;
    }
    
    @JsonProperty("monthly_out")
    private BigDecimal monthlyOut;
    
    public BigDecimal getMonthlyOut() {
        return monthlyOut;
    }
    
    @JsonProperty("stats_op_type")
    private Enum statsOpType;
    
    public Enum getStatsOpType() {
        return statsOpType;
    }
    
    @JsonProperty("weekly_out")
    private BigDecimal weeklyOut;
    
    public BigDecimal getWeeklyOut() {
        return weeklyOut;
    }
}
