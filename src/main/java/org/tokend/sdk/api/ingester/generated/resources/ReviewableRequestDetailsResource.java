// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("request-details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewableRequestDetailsResource extends BaseResource {
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("sequence_number")
    private Long sequenceNumber;
    
    public Long getSequenceNumber() {
        return sequenceNumber;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            sequenceNumber != null 
        ;
    }
    
    @Relationship("operations")
    private List<BaseResource> operations;
    
    public List<? extends BaseResource> getOperations() {
        return operations;
    }
}
