// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("requests")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewableRequestResource extends BaseResource {
    
    @JsonProperty("all_tasks")
    private Long allTasks;
    
    public Long getAllTasks() {
        return allTasks;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @JsonProperty("hash")
    private String hash;
    
    public String getHash() {
        return hash;
    }
    
    @JsonProperty("pending_tasks")
    private Long pendingTasks;
    
    public Long getPendingTasks() {
        return pendingTasks;
    }
    
    @JsonProperty("reject_reason")
    private String rejectReason;
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
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
    
    @JsonProperty("updated_at")
    private Date updatedAt;
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    @Override
    public boolean isFilled() {
        return             allTasks != null &&
            createdAt != null &&
            externalDetails != null &&
            hash != null &&
            pendingTasks != null &&
            rejectReason != null &&
            securityType != null &&
            state != null &&
            stateI != null &&
            updatedAt != null 
        ;
    }
    
    @Relationship("request_details")
    private ReviewableRequestDetailsResource requestDetails;
    
    public ReviewableRequestDetailsResource getRequestDetails() {
        return requestDetails;
    }
    
    @Relationship("requestor")
    private AccountResource requestor;
    
    public AccountResource getRequestor() {
        return requestor;
    }
}
