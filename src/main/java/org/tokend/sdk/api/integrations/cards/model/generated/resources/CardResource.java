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


@Type("cards")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardResource extends BaseResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("state")
    private org.tokend.sdk.api.integrations.cards.model.generated.inner.Enum state;
    
    public org.tokend.sdk.api.integrations.cards.model.generated.inner.Enum getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            state != null 
        ;
    }
    
    @Relationship("balances")
    private List<CardBalanceResource> balances;
    
    public List<? extends CardBalanceResource> getBalances() {
        return balances;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
