package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

@Type("operations-create-kyc-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateKycRequestOpDetailsResource extends OperationDetailsResource {
    // TODO: Find out type
    @JsonProperty("account_type_to_set")
    private JsonNode accountTypeToSet;

    @JsonProperty("kyc_data")
    private JsonNode kycData;

    @JsonProperty("all_tasks")
    private Long allTasks;

    @Relationship("account_to_update_kyc")
    private AccountResource accountToUpdateKyc;

    @Relationship("request")
    private ReviewableRequestResource request;

    public JsonNode getAccountTypeToSet() {
        return accountTypeToSet;
    }

    public JsonNode getKycData() {
        return kycData;
    }

    public Long getAllTasks() {
        return allTasks;
    }

    public AccountResource getAccountToUpdateKyc() {
        return accountToUpdateKyc;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    @Override
    public boolean hasAttributes() {
        return accountTypeToSet != null;
    }
}
