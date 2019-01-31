package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.sales.model.SaleResource;

@Type("operations-check-sale-state")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckSaleStateOpDetailsResource extends OperationDetailsResource {
    // TODO: Find out type
    @JsonProperty("effect")
    private JsonNode effect;

    @Relationship("sale")
    private SaleResource sale;

    public JsonNode getEffect() {
        return effect;
    }

    public SaleResource getSale() {
        return sale;
    }

    @Override
    public boolean hasAttributes() {
        return effect != null;
    }
}
