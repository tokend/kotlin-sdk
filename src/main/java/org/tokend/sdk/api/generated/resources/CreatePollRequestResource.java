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


@Type("request-details-create-poll")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePollRequestResource extends BaseReviewableRequestDetailsResource {
    
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
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            endTime != null &&
            numberOfChoices != null &&
            permissionType != null &&
            pollData != null &&
            startTime != null &&
            voteConfirmationRequired != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("result_provider")
    private AccountResource resultProvider;
    
    public AccountResource getResultProvider() {
        return resultProvider;
    }
}
