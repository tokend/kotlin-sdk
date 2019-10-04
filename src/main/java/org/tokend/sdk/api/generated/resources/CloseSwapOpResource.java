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


@Type("operations-close-swap")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloseSwapOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("secret")
    @Nullable
    private String secret;
    
    @Nullable
    public String getSecret() {
        return secret;
    }
    
    @Override
    public boolean isFilled() {
        return             secret != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("swap")
    private SwapResource swap;
    
    public SwapResource getSwap() {
        return swap;
    }
}
