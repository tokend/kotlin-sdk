// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("votes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteResource extends BaseResource {
    
    @JsonProperty("vote_data")
    private VoteData voteData;
    
    public VoteData getVoteData() {
        return voteData;
    }
    
    @Override
    public boolean isFilled() {
        return             voteData != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("poll")
    private PollResource poll;
    
    public PollResource getPoll() {
        return poll;
    }
    
    @JsonIgnore
    @Relationship("voter")
    private AccountResource voter;
    
    public AccountResource getVoter() {
        return voter;
    }
}
