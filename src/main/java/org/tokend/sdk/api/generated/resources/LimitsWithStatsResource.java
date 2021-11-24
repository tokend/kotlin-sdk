// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("limits-with-stats")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitsWithStatsResource extends BaseResource {
    
    @JsonProperty("limits")
    private LimitsResource limits;
    
    public LimitsResource getLimits() {
        return limits;
    }
    
    @JsonProperty("statistics")
    private StatisticsResource statistics;
    
    public StatisticsResource getStatistics() {
        return statistics;
    }
    
    @Override
    public boolean isFilled() {
        return             limits != null &&
            statistics != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
}
