// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
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
    
    @JsonProperty("xdr_type")
    private org.tokend.sdk.api.generated.inner.Enum xdrType;
    
    public org.tokend.sdk.api.generated.inner.Enum getXdrType() {
        return xdrType;
    }
    
    @Override
    public boolean isFilled() {
        return             allTasks != null &&
            createdAt != null &&
            externalDetails != null &&
            hash != null &&
            pendingTasks != null &&
            rejectReason != null &&
            state != null &&
            stateI != null &&
            updatedAt != null &&
            xdrType != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("request_details")
    private BaseReviewableRequestDetailsResource requestDetails;
    
    public BaseReviewableRequestDetailsResource getRequestDetails() {
        return requestDetails;
    }
    
    @JsonIgnore
    @Relationship("requestor")
    private AccountResource requestor;
    
    public AccountResource getRequestor() {
        return requestor;
    }
    
    @JsonIgnore
    @Relationship("reviewer")
    private AccountResource reviewer;
    
    public AccountResource getReviewer() {
        return reviewer;
    }
}
