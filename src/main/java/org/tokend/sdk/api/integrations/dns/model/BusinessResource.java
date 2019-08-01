package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("businesses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessResource extends BaseResource {
    private BusinessResource() {}

    public BusinessResource(String accountId) {
        this.id = accountId;
        this.accountId = accountId;
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("logo")
    private String logoJson;

    @JsonProperty("industry")
    private String industry;

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getLogoJson() {
        return logoJson;
    }

    public String getIndustry() {
        return industry;
    }

    @Override
    public boolean isFilled() {
        return name != null && accountId != null && logoJson != null && industry != null;
    }
}
