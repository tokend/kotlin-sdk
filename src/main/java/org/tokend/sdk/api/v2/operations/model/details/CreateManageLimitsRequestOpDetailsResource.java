package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

@Type("operations-create-manage-limit")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateManageLimitsRequestOpDetailsResource extends OperationDetailsResource {
    // TODO: Find out type
    @JsonProperty("data")
    private JsonNode data;

    @Relationship("request")
    private ReviewableRequestResource request;

    public JsonNode getData() {
        return data;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    @Override
    public boolean hasAttributes() {
        return data != null;
    }
}
