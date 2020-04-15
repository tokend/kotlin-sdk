// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.recpayments.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("recurring-payments-svc-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResource extends BaseResource {
    
    @JsonProperty("account")
    private String account;
    
    public String getAccount() {
        return account;
    }
    
    @Override
    public boolean isFilled() {
        return             account != null 
        ;
    }
}
