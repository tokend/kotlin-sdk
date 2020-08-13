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


@Type("transcations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResource extends BaseResource {
    
    @JsonProperty("env")
    private String env;
    
    public String getEnv() {
        return env;
    }
    
    @Override
    public boolean isFilled() {
        return             env != null 
        ;
    }
    
    @Relationship("destination_card")
    private CardResource destinationCard;
    
    public CardResource getDestinationCard() {
        return destinationCard;
    }
    
    @Relationship("source_card")
    private CardResource sourceCard;
    
    public CardResource getSourceCard() {
        return sourceCard;
    }
}
