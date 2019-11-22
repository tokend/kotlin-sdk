// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LedgerInfo {
    
    @JsonProperty("last_ledger_increase_time")
    private Date lastLedgerIncreaseTime;
    
    public Date getLastLedgerIncreaseTime() {
        return lastLedgerIncreaseTime;
    }
    
    @JsonProperty("latest")
    private Long latest;
    
    public Long getLatest() {
        return latest;
    }
    
    @JsonProperty("oldest_on_start")
    private Long oldestOnStart;
    
    public Long getOldestOnStart() {
        return oldestOnStart;
    }
}
