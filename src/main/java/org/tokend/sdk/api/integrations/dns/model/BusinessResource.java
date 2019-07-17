package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("businesses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessResource extends BaseResource {
    @JsonProperty("name")
    private String name;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("logo")
    private String logoJson;

    public String getName() {
        return name;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getLogoJson() {
        return logoJson;
    }

    @Override
    public boolean isFilled() {
        return name != null && accountId != null && logoJson != null;
    }
}
