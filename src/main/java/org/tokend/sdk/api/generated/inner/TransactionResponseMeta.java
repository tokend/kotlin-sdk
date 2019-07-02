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
public class TransactionResponseMeta {
    
    @JsonProperty("latest_ledger_close_time")
    private Date latestLedgerCloseTime;
    
    public Date getLatestLedgerCloseTime() {
        return latestLedgerCloseTime;
    }
    
    @JsonProperty("latest_ledger_sequence")
    private Integer latestLedgerSequence;
    
    public Integer getLatestLedgerSequence() {
        return latestLedgerSequence;
    }
}
