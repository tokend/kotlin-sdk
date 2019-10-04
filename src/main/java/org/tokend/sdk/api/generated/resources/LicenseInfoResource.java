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


@Type("license-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoResource extends BaseResource {
    
    @JsonProperty("admin_count")
    private Long adminCount;
    
    public Long getAdminCount() {
        return adminCount;
    }
    
    @JsonProperty("current_admin_count")
    private Long currentAdminCount;
    
    public Long getCurrentAdminCount() {
        return currentAdminCount;
    }
    
    @JsonProperty("due_date")
    private Date dueDate;
    
    public Date getDueDate() {
        return dueDate;
    }
    
    @Override
    public boolean isFilled() {
        return             adminCount != null &&
            currentAdminCount != null &&
            dueDate != null 
        ;
    }
}
