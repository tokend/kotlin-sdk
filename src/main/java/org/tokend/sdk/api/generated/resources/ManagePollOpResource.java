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


@Type("operations-manage-poll")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManagePollOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("close")
    @Nullable
    private ClosePollOp close;
    
    @Nullable
    public ClosePollOp getClose() {
        return close;
    }
    
    @JsonProperty("poll_id")
    private Long pollId;
    
    public Long getPollId() {
        return pollId;
    }
    
    @JsonProperty("update_end_time")
    @Nullable
    private UpdatePollEndTimeOp updateEndTime;
    
    @Nullable
    public UpdatePollEndTimeOp getUpdateEndTime() {
        return updateEndTime;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            pollId != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("poll")
    private PollResource poll;
    
    public PollResource getPoll() {
        return poll;
    }
}
