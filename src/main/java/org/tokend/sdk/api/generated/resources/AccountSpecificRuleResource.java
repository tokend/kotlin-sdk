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


@Type("account-specific-rules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSpecificRuleResource extends BaseResource {
    
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
    
    @Override
    public boolean isFilled() {
        return             forbids != null &&
            ledgerKey != null 
        ;
    }
}
