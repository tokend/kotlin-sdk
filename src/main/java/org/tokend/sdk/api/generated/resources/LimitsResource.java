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


@Type("limits")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitsResource extends BaseResource {
    
    @JsonProperty("annual_out")
    private BigDecimal annualOut;
    
    public BigDecimal getAnnualOut() {
        return annualOut;
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
    private Integer statsOpType;
    
    public Integer getStatsOpType() {
        return statsOpType;
    }
    
    @JsonProperty("weekly_out")
    private BigDecimal weeklyOut;
    
    public BigDecimal getWeeklyOut() {
        return weeklyOut;
    }
    
    @Override
    public boolean isFilled() {
        return             annualOut != null &&
            dailyOut != null &&
            isConvertNeeded != null &&
            monthlyOut != null &&
            statsOpType != null &&
            weeklyOut != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @Relationship("account_role")
    private AccountResource accountRole;
    
    public AccountResource getAccountRole() {
        return accountRole;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
