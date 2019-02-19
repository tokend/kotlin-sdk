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


@Type("external-system-ids")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalSystemIdResource extends BaseResource {
    
    @JsonProperty("external_system_type")
    private Integer externalSystemType;
    
    public Integer getExternalSystemType() {
        return externalSystemType;
    }
    
    @JsonProperty("data")
    private String data;
    
    public String getData() {
        return data;
    }
    
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    
    public Boolean isDeleted() {
        return isDeleted;
    }
    
    @JsonProperty("expires_at")
    private Date expiresAt;
    
    public Date getExpiresAt() {
        return expiresAt;
    }
    
    @JsonProperty("binded_at")
    private Date bindedAt;
    
    public Date getBindedAt() {
        return bindedAt;
    }
    
    @Override
    public boolean hasAttributes() {
        return             externalSystemType != null &&
            data != null &&
            isDeleted != null &&
            expiresAt != null &&
            bindedAt != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
}
