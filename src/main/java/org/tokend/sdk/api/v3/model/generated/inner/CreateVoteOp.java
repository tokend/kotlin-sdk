// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateVoteOp {
    
    @JsonProperty("poll_id")
    private Long pollId;
    
    public Long getPollId() {
        return pollId;
    }
    
    @JsonProperty("vote_data")
    private VoteData voteData;
    
    public VoteData getVoteData() {
        return voteData;
    }
}
