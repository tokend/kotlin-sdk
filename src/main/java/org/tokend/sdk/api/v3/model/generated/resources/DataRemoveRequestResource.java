// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("request-details-data-remove")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataRemoveRequestResource extends BaseReviewableRequestDetailsResource {
    
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
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("data")
    private DataResource data;
    
    public DataResource getData() {
        return data;
    }
}
