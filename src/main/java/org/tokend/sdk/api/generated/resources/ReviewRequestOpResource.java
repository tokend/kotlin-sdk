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


@Type("operations-review-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("added_tasks")
    private Long addedTasks;
    
    public Long getAddedTasks() {
        return addedTasks;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @JsonProperty("is_fulfilled")
    private Boolean isFulfilled;
    
    public Boolean isFulfilled() {
        return isFulfilled;
    }
    
    @JsonProperty("reason")
    private String reason;
    
    public String getReason() {
        return reason;
    }
    
    @JsonProperty("removed_tasks")
    private Long removedTasks;
    
    public Long getRemovedTasks() {
        return removedTasks;
    }
    
    @JsonProperty("request_hash")
    private String requestHash;
    
    public String getRequestHash() {
        return requestHash;
    }
    
    @JsonProperty("request_id")
    private Long requestId;
    
    public Long getRequestId() {
        return requestId;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            addedTasks != null &&
            externalDetails != null &&
            isFulfilled != null &&
            reason != null &&
            removedTasks != null &&
            requestHash != null &&
            requestId != null 
            && super.isFilled()
        ;
    }
}
