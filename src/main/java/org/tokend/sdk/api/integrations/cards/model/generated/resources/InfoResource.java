// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.cards.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.cards.model.generated.resources.*;
import org.tokend.sdk.api.integrations.cards.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("cards-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("account")
    private BaseResource account;
    
    public BaseResource getAccount() {
        return account;
    }
    
    @Relationship("role")
    private BaseResource role;
    
    public BaseResource getRole() {
        return role;
    }
    
    @Relationship("signer")
    private BaseResource signer;
    
    public BaseResource getSigner() {
        return signer;
    }
}
