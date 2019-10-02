// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("account-kyc")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountKYCResource extends BaseResource {
    
    @JsonProperty("kyc_data")
    private JsonNode kycData;
    
    public JsonNode getKycData() {
        return kycData;
    }
    
    @Override
    public boolean isFilled() {
        return             kycData != null 
        ;
    }
}
