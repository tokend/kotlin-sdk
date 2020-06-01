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


@Type("cards-security-details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardSecurityDetailsResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("card")
    private CardResource card;
    
    public CardResource getCard() {
        return card;
    }
}
