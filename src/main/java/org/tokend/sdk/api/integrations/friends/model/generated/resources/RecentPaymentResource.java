// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.friends.model.generated.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;


@Type("recent-payments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecentPaymentResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
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
