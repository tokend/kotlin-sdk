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


@Type("poll-outcome")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PollParticipationResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("votes")
    private List<VoteResource> votes;
    
    public List<? extends VoteResource> getVotes() {
        return votes;
    }
}
