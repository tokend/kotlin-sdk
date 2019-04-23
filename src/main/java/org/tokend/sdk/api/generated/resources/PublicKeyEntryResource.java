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


@Type("public-key-entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicKeyEntryResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("account")
    private List<AccountResource> account;
    
    public List<? extends AccountResource> getAccount() {
        return account;
    }
}
