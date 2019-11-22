// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePollRequestOp {
    
    @JsonProperty("all_tasks")
    @Nullable
    private Long allTasks;
    
    @Nullable
    public Long getAllTasks() {
        return allTasks;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("number_of_choices")
    private Long numberOfChoices;
    
    public Long getNumberOfChoices() {
        return numberOfChoices;
    }
    
    @JsonProperty("permission_type")
    private Long permissionType;
    
    public Long getPermissionType() {
        return permissionType;
    }
    
    @JsonProperty("poll_data")
    private PollData pollData;
    
    public PollData getPollData() {
        return pollData;
    }
    
    @JsonProperty("result_provider_id")
    private String resultProviderId;
    
    public String getResultProviderId() {
        return resultProviderId;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @JsonProperty("vote_confirmation_required")
    private Boolean voteConfirmationRequired;
    
    public Boolean voteConfirmationRequired() {
        return voteConfirmationRequired;
    }
}
