// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("requests")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewableRequestResource extends BaseResource {
    
    @JsonProperty("reference")
    @Nullable
    private String reference;
    
    @Nullable
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("reject_reason")
    private String rejectReason;
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    @JsonProperty("hash")
    private String hash;
    
    public String getHash() {
        return hash;
    }
    
    @JsonProperty("all_tasks")
    private Long allTasks;
    
    public Long getAllTasks() {
        return allTasks;
    }
    
    @JsonProperty("pending_tasks")
    private Long pendingTasks;
    
    public Long getPendingTasks() {
        return pendingTasks;
    }
    
    @JsonProperty("external_details")
    @Nullable
    private JsonNode externalDetails;
    
    @Nullable
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("updated_at")
    private Date updatedAt;
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    @JsonProperty("state")
    private String state;
    
    public String getState() {
        return state;
    }
    
    @JsonProperty("state_i")
    private Integer stateI;
    
    public Integer getStateI() {
        return stateI;
    }
    
    @JsonProperty("xdr_type")
    private XdrEnumValue xdrType;
    
    public XdrEnumValue getXdrType() {
        return xdrType;
    }
    
    @Override
    public boolean hasAttributes() {
        return             rejectReason != null &&
            hash != null &&
            allTasks != null &&
            pendingTasks != null &&
            createdAt != null &&
            updatedAt != null &&
            state != null &&
            stateI != null &&
            xdrType != null 
        ;
    }
    
    @Relationship("requestor")
    private AccountResource requestor;
    
    public AccountResource getRequestor() {
        return requestor;
    }
    
    @Relationship("reviewer")
    private AccountResource reviewer;
    
    public AccountResource getReviewer() {
        return reviewer;
    }
    
    @Relationship("request_details")
    private RequestDetailsResource requestDetails;
    
    public RequestDetailsResource getRequestDetails() {
        return requestDetails;
    }
}
