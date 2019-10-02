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
public class CreateAccountSpecificRuleData {
    
    @JsonProperty("account_id")
    private String accountId;
    
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
