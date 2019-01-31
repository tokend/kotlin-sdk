package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("operations-manage_account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageAccountOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("account_address")
    private String accountAddress;

    // TODO: Find out type
    @JsonProperty("block_reasons_to_add")
    private JsonNode blockReasonsToAdd;

    // TODO: Find out type
    @JsonProperty("block_reasons_to_remove")
    private JsonNode blockReasonsToRemove;

    @Override
    public boolean hasAttributes() {
        return accountAddress != null;
    }
}
