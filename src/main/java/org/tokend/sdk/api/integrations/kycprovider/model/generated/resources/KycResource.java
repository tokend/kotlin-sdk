// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.kycprovider.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.kycprovider.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("kyc")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KycResource extends BaseResource {
    
    @JsonProperty("account_id")
    private String accountId;
    
    public String getAccountId() {
        return accountId;
    }
    
    @JsonProperty("details")
    @Nullable
    private JsonNode details;
    
    @Nullable
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("role")
    private Integer role;
    
    public Integer getRole() {
        return role;
    }
    
    @Override
    public boolean isFilled() {
        return             accountId != null &&
            role != null 
        ;
    }
}
