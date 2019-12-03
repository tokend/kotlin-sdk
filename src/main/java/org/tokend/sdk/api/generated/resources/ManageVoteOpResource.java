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


@Type("operations-manage-vote")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageVoteOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private CreateVoteOp create;
    
    @Nullable
    public CreateVoteOp getCreate() {
        return create;
    }
    
    @JsonProperty("remove")
    @Nullable
    private RemoveVoteOp remove;
    
    @Nullable
    public RemoveVoteOp getRemove() {
        return remove;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("poll")
    private PollResource poll;
    
    public PollResource getPoll() {
        return poll;
    }
    
    @Relationship("result_provider")
    private AccountResource resultProvider;
    
    public AccountResource getResultProvider() {
        return resultProvider;
    }
    
    @Relationship("voter")
    private AccountResource voter;
    
    public AccountResource getVoter() {
        return voter;
    }
}
