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


@Type("invitations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvitationResource extends BaseResource {
    
    @JsonProperty("accepted_at")
    @Nullable
    private Date acceptedAt;
    
    @Nullable
    public Date getAcceptedAt() {
        return acceptedAt;
    }
    
    @JsonProperty("cancelled_at")
    @Nullable
    private Date cancelledAt;
    
    @Nullable
    public Date getCancelledAt() {
        return cancelledAt;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("from")
    private Date from;
    
    public Date getFrom() {
        return from;
    }
    
    @JsonProperty("holds_allowed")
    private Integer holdsAllowed;
    
    public Integer getHoldsAllowed() {
        return holdsAllowed;
    }
    
    @JsonProperty("holds_left")
    private Integer holdsLeft;
    
    public Integer getHoldsLeft() {
        return holdsLeft;
    }
    
    @JsonProperty("paid_at")
    @Nullable
    private Date paidAt;
    
    @Nullable
    public Date getPaidAt() {
        return paidAt;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("state")
    @Nullable
    private org.tokend.sdk.api.integrations.invitations.model.generated.inner.Enum state;
    
    @Nullable
    public org.tokend.sdk.api.integrations.invitations.model.generated.inner.Enum getState() {
        return state;
    }
    
    @JsonProperty("to")
    private Date to;
    
    public Date getTo() {
        return to;
    }
    
    @JsonProperty("updated_at")
    @Nullable
    private Date updatedAt;
    
    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    @JsonProperty("wait_until")
    @Nullable
    private Date waitUntil;
    
    @Nullable
    public Date getWaitUntil() {
        return waitUntil;
    }
    
    @Override
    public boolean isFilled() {
        return             createdAt != null &&
            details != null &&
            from != null &&
            holdsAllowed != null &&
            holdsLeft != null &&
            reference != null &&
            to != null 
        ;
    }
    
    @Relationship("data")
    private BaseResource data;
    
    public BaseResource getData() {
        return data;
    }
    
    @Relationship("guest")
    private BaseResource guest;
    
    public BaseResource getGuest() {
        return guest;
    }
    
    @Relationship("host")
    private BaseResource host;
    
    public BaseResource getHost() {
        return host;
    }
    
    @Relationship("place")
    private BaseResource place;
    
    public BaseResource getPlace() {
        return place;
    }
}
