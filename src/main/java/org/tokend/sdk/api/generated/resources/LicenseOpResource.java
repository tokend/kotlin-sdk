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


@Type("operations-license")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("admin_count")
    private Long adminCount;
    
    public Long getAdminCount() {
        return adminCount;
    }
    
    @JsonProperty("due_date")
    private Date dueDate;
    
    public Date getDueDate() {
        return dueDate;
    }
    
    @JsonProperty("ledger_hash")
    private String ledgerHash;
    
    public String getLedgerHash() {
        return ledgerHash;
    }
    
    @JsonProperty("prev_license_hash")
    private String prevLicenseHash;
    
    public String getPrevLicenseHash() {
        return prevLicenseHash;
    }
    
    @JsonProperty("signatures")
    private List<String> signatures;
    
    public List<? extends String> getSignatures() {
        return signatures;
    }
    
    @Override
    public boolean isFilled() {
        return             adminCount != null &&
            dueDate != null &&
            ledgerHash != null &&
            prevLicenseHash != null &&
            signatures != null 
            && super.isFilled()
        ;
    }
}
