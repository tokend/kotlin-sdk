// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("external-system-ids")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalSystemIDResource extends BaseResource {
    
    @JsonProperty("binded_at")
    private Date bindedAt;
    
    public Date getBindedAt() {
        return bindedAt;
    }
    
    @JsonProperty("data")
    private ExternalSystemData data;
    
    public ExternalSystemData getData() {
        return data;
    }
    
    @JsonProperty("expires_at")
    private Date expiresAt;
    
    public Date getExpiresAt() {
        return expiresAt;
    }
    
    @JsonProperty("external_system_type")
    private Integer externalSystemType;
    
    public Integer getExternalSystemType() {
        return externalSystemType;
    }
    
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    
    public Boolean isDeleted() {
        return isDeleted;
    }
    
    @Override
    public boolean isFilled() {
        return             bindedAt != null &&
            data != null &&
            expiresAt != null &&
            externalSystemType != null &&
            isDeleted != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
}
