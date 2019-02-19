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


@Type("request-details-update-kyc")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateKYCRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("account_type_to_set")
    private XdrEnumValue accountTypeToSet;
    
    public XdrEnumValue getAccountTypeToSet() {
        return accountTypeToSet;
    }
    
    @JsonProperty("kyc_level")
    private Long kycLevel;
    
    public Long getKycLevel() {
        return kycLevel;
    }
    
    @JsonProperty("kyc_data")
    private JsonNode kycData;
    
    public JsonNode getKycData() {
        return kycData;
    }
    
    @JsonProperty("sequence_number")
    private Long sequenceNumber;
    
    public Long getSequenceNumber() {
        return sequenceNumber;
    }
    
    @JsonProperty("external_details")
    private List<JsonNode> externalDetails;
    
    public List<? extends JsonNode> getExternalDetails() {
        return externalDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             accountTypeToSet != null &&
            kycLevel != null &&
            kycData != null &&
            sequenceNumber != null &&
            externalDetails != null 
        ;
    }
    
    @Relationship("account_to_update_kyc")
    private AccountResource accountToUpdateKyc;
    
    public AccountResource getAccountToUpdateKyc() {
        return accountToUpdateKyc;
    }
}
