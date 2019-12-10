// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("public-key-entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicKeyEntryResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("accounts")
    private List<AccountResource> accounts;
    
    public List<? extends AccountResource> getAccounts() {
        return accounts;
    }
}
