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


@Type("converted-balances-collections")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertedBalancesCollectionResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("states")
    private List<ConvertedBalanceStateResource> states;
    
    public List<? extends ConvertedBalanceStateResource> getStates() {
        return states;
    }
}
