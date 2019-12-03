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


@Type("operations-manage-account-specific-rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageAccountSpecificRuleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("create_data")
    @Nullable
    private CreateAccountSpecificRuleData createData;
    
    @Nullable
    public CreateAccountSpecificRuleData getCreateData() {
        return createData;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("rule")
    private AccountSpecificRuleResource rule;
    
    public AccountSpecificRuleResource getRule() {
        return rule;
    }
}
