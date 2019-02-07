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


@Type("accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource extends BaseResource {
    @Override
    public boolean hasAttributes() { return false; }
    
    @Relationship("role")
    private RoleResource role;
    
    public RoleResource getRole() {
        return role;
    }
    
    @Relationship("balances")
    private List<BalanceResource> balances;
    
    public List<? extends BalanceResource> getBalances() {
        return balances;
    }
    
    @Relationship("referrer")
    private AccountResource referrer;
    
    public AccountResource getReferrer() {
        return referrer;
    }
}
