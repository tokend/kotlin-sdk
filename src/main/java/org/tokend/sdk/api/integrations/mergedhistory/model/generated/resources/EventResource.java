// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources.*;
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("aggregated-event")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends BaseResource {
    
    @JsonProperty("body")
    private JsonNode body;
    
    public JsonNode getBody() {
        return body;
    }
    
    @JsonProperty("collection")
    private String collection;
    
    public String getCollection() {
        return collection;
    }
    
    @JsonProperty("occurred_at")
    private Date occurredAt;
    
    public Date getOccurredAt() {
        return occurredAt;
    }
    
    @Override
    public boolean isFilled() {
        return             body != null &&
            collection != null &&
            occurredAt != null 
        ;
    }
    
    @Relationship("participants")
    private List<BaseResource> participants;
    
    public List<? extends BaseResource> getParticipants() {
        return participants;
    }
}
