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


@Type("invoice-events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("signature")
    @Nullable
    private String signature;
    
    @Nullable
    public String getSignature() {
        return signature;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            createdAt != null 
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
    
    @Relationship("invoice")
    private InvoiceResource invoice;
    
    public InvoiceResource getInvoice() {
        return invoice;
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
