// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("transactions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResource extends BaseResource {
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("envelope_xdr")
    private String envelopeXdr;
    
    public String getEnvelopeXdr() {
        return envelopeXdr;
    }
    
    @JsonProperty("hash")
    private String hash;
    
    public String getHash() {
        return hash;
    }
    
    @JsonProperty("ledger_sequence")
    private Integer ledgerSequence;
    
    public Integer getLedgerSequence() {
        return ledgerSequence;
    }
    
    @JsonProperty("result_meta_xdr")
    private String resultMetaXdr;
    
    public String getResultMetaXdr() {
        return resultMetaXdr;
    }
    
    @JsonProperty("result_xdr")
    private String resultXdr;
    
    public String getResultXdr() {
        return resultXdr;
    }
    
    @Override
    public boolean isFilled() {
        return             createdAt != null &&
            envelopeXdr != null &&
            hash != null &&
            ledgerSequence != null &&
            resultMetaXdr != null &&
            resultXdr != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("ledger_entry_changes")
    private List<LedgerEntryChangeResource> ledgerEntryChanges;
    
    public List<? extends LedgerEntryChangeResource> getLedgerEntryChanges() {
        return ledgerEntryChanges;
    }
    
    @JsonIgnore
    @Relationship("operations")
    private List<OperationResource> operations;
    
    public List<? extends OperationResource> getOperations() {
        return operations;
    }
    
    @JsonIgnore
    @Relationship("source")
    private AccountResource source;
    
    public AccountResource getSource() {
        return source;
    }
}
