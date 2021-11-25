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


@Type("request-details-data-creation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataCreationRequestResource extends BaseReviewableRequestDetailsResource {
    
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
    
    @JsonProperty("type")
    private Long type;
    
    public Long getType() {
        return type;
    }
    
    @JsonProperty("value")
    private JsonNode value;
    
    public JsonNode getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            sequenceNumber != null &&
            type != null &&
            value != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
