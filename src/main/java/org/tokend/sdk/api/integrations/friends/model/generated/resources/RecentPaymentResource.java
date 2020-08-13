// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.friends.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.friends.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("recent-payments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecentPaymentResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("destination")
    private BaseResource destination;
    
    public BaseResource getDestination() {
        return destination;
    }
    
    @Relationship("destination_card")
    private BaseResource destinationCard;
    
    public BaseResource getDestinationCard() {
        return destinationCard;
    }
    
    @Relationship("source")
    private BaseResource source;
    
    public BaseResource getSource() {
        return source;
    }
}
