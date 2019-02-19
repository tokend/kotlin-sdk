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


@Type("operations-create-kyc-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreateKYCRequestDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("account_type_to_set")
    private XdrEnumValue accountTypeToSet;
    
    public XdrEnumValue getAccountTypeToSet() {
        return accountTypeToSet;
    }
    
    @JsonProperty("kyc_data")
    private JsonNode kycData;
    
    public JsonNode getKycData() {
        return kycData;
    }
    
    @JsonProperty("all_tasks")
    @Nullable
    private Integer allTasks;
    
    @Nullable
    public Integer getAllTasks() {
        return allTasks;
    }
    
    @Override
    public boolean hasAttributes() {
        return             accountTypeToSet != null &&
            kycData != null 
        ;
    }
    
    @Relationship("account_to_update_kyc")
    private AccountResource accountToUpdateKyc;
    
    public AccountResource getAccountToUpdateKyc() {
        return accountToUpdateKyc;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
