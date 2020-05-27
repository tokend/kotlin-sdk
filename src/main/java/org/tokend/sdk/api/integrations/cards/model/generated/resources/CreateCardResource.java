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


@Type("add-card-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCardResource extends BaseResource {
    
    @JsonProperty("card_number")
    private String cardNumber;
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    @Override
    public boolean isFilled() {
        return             cardNumber != null 
        ;
    }
    
    @Relationship("balances")
    private List<BaseResource> balances;
    
    public List<? extends BaseResource> getBalances() {
        return balances;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
