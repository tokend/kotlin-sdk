// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.invoices.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.invoices.model.generated.resources.*;
import org.tokend.sdk.api.integrations.invoices.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("invoices")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("cancelled_at")
    @Nullable
    private Date cancelledAt;
    
    @Nullable
    public Date getCancelledAt() {
        return cancelledAt;
    }
    
    @JsonProperty("completed_at")
    @Nullable
    private Date completedAt;
    
    @Nullable
    public Date getCompletedAt() {
        return completedAt;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("rejected_at")
    @Nullable
    private Date rejectedAt;
    
    @Nullable
    public Date getRejectedAt() {
        return rejectedAt;
    }
    
    @JsonProperty("state")
    private org.tokend.sdk.api.integrations.invoices.model.generated.inner.Enum state;
    
    public org.tokend.sdk.api.integrations.invoices.model.generated.inner.Enum getState() {
        return state;
    }
    
    @JsonProperty("subject")
    private String subject;
    
    public String getSubject() {
        return subject;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            createdAt != null &&
            reference != null &&
            state != null &&
            subject != null 
        ;
    }
    
    @Relationship("asset")
    private BaseResource asset;
    
    public BaseResource getAsset() {
        return asset;
    }
    
    @Relationship("data")
    private DataResource data;
    
    public DataResource getData() {
        return data;
    }
    
    @Relationship("destination_card")
    private BaseResource destinationCard;
    
    public BaseResource getDestinationCard() {
        return destinationCard;
    }
    
    @Relationship("requestor")
    private BaseResource requestor;
    
    public BaseResource getRequestor() {
        return requestor;
    }
    
    @Relationship("target")
    private BaseResource target;
    
    public BaseResource getTarget() {
        return target;
    }
}
