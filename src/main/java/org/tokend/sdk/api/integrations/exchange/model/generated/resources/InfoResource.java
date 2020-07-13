// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.exchange.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("currency-exchange-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("pairs")
    private List<BaseResource> pairs;
    
    public List<? extends BaseResource> getPairs() {
        return pairs;
    }
}
