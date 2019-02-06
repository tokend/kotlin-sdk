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
public class OpReviewRequestDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
        return action;
    }
    
    @JsonProperty("reason")
    private String reason;
    
    public String getReason() {
        return reason;
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
    
    @JsonProperty("is_fulfilled")
    private Boolean isFulfilled;
    
    public Boolean isFulfilled() {
        return isFulfilled;
    }
    
    @JsonProperty("added_tasks")
    private Long addedTasks;
    
    public Long getAddedTasks() {
        return addedTasks;
    }
    
    @JsonProperty("removed_tasks")
    private Long removedTasks;
    
    public Long getRemovedTasks() {
        return removedTasks;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             action != null &&
            reason != null &&
            requestHash != null &&
            requestId != null &&
            isFulfilled != null &&
            addedTasks != null &&
            removedTasks != null &&
            externalDetails != null 
        ;
    }
}
