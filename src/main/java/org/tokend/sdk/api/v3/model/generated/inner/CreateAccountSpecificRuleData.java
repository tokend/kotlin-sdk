// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountSpecificRuleData {
    
    @JsonProperty("account_id")
    @Nullable
    private String accountId;
    
    @Nullable
    public String getAccountId() {
        return accountId;
    }
    
    @JsonProperty("forbids")
    private Boolean forbids;
    
    public Boolean forbids() {
        return forbids;
    }
    
    @JsonProperty("ledger_key")
    private JsonNode ledgerKey;
    
    public JsonNode getLedgerKey() {
        return ledgerKey;
    }
}
