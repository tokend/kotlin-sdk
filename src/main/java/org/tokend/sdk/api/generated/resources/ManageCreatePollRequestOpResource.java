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


@Type("operations-manage-create-poll-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageCreatePollRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private CreatePollRequestOp create;
    
    @Nullable
    public CreatePollRequestOp getCreate() {
        return create;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
    
    @Relationship("result_provider")
    private AccountResource resultProvider;
    
    public AccountResource getResultProvider() {
        return resultProvider;
    }
}
