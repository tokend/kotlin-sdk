// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.invitations.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.*;
import org.tokend.sdk.api.integrations.invitations.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends BaseResource {
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("details")
    @Nullable
    private JsonNode details;
    
    @Nullable
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("signature")
    private String signature;
    
    public String getSignature() {
        return signature;
    }
    
    @JsonProperty("type")
    private Integer type;
    
    public Integer getType() {
        return type;
    }
    
    @Override
    public boolean isFilled() {
        return             createdAt != null &&
            signature != null &&
            type != null 
        ;
    }
    
    @Relationship("aggregate")
    private InvitationResource aggregate;
    
    public InvitationResource getAggregate() {
        return aggregate;
    }
}
