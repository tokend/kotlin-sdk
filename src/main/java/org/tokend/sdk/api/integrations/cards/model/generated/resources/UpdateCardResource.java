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


@Type("update-card-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCardResource extends BaseResource {
    
    @JsonProperty("state")
    @Nullable
    private org.tokend.sdk.api.integrations.cards.model.generated.inner.Enum state;
    
    @Nullable
    public org.tokend.sdk.api.integrations.cards.model.generated.inner.Enum getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             state != null 
        ;
    }
    
    @Relationship("bind_balances")
    private List<BaseResource> bindBalances;
    
    public List<? extends BaseResource> getBindBalances() {
        return bindBalances;
    }
    
    @Relationship("unbind_balances")
    private List<BaseResource> unbindBalances;
    
    public List<? extends BaseResource> getUnbindBalances() {
        return unbindBalances;
    }
}
