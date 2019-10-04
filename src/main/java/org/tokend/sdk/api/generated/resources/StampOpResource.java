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


@Type("operations-stamp")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StampOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("ledger_hash")
    private String ledgerHash;
    
    public String getLedgerHash() {
        return ledgerHash;
    }
    
    @JsonProperty("license_hash")
    private String licenseHash;
    
    public String getLicenseHash() {
        return licenseHash;
    }
    
    @Override
    public boolean isFilled() {
        return             ledgerHash != null &&
            licenseHash != null 
            && super.isFilled()
        ;
    }
}
