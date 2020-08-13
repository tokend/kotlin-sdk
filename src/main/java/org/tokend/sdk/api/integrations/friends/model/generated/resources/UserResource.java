// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.friends.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.friends.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends BaseResource {
    
    @JsonProperty("identifier_value")
    private String identifierValue;
    
    public String getIdentifierValue() {
        return identifierValue;
    }
    
    @Override
    public boolean isFilled() {
        return             identifierValue != null 
        ;
    }
    
    @Relationship("account")
    private BaseResource account;
    
    public BaseResource getAccount() {
        return account;
    }
    
    @Relationship("identity")
    private BaseResource identity;
    
    public BaseResource getIdentity() {
        return identity;
    }
}
