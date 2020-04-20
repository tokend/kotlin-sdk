// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.recpayments.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.resources.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("scheduled-payment-record-description")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledPaymentRecordDescriptionResource extends BaseResource {
    
    @JsonProperty("next_time_send")
    private Date nextTimeSend;
    
    public Date getNextTimeSend() {
        return nextTimeSend;
    }
    
    @JsonProperty("r_rule")
    private String rRule;
    
    public String getRRule() {
        return rRule;
    }
    
    @Override
    public boolean isFilled() {
        return             nextTimeSend != null &&
            rRule != null 
        ;
    }
}
