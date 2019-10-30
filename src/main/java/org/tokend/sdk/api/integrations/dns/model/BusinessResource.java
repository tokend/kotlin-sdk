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

    @JsonProperty("stats_quote_asset")
    private String statsQuoteAsset;

    @JsonProperty("description")
    private String description;

    @JsonProperty("bravo")
    private String bannerJson;

    @JsonProperty("status")
    private String status;

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

    public String getStatsQuoteAsset() {
        return statsQuoteAsset;
    }

    public String getDescription() {
        return description;
    }

    public String getBannerJson() {
        return bannerJson;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean isFilled() {
        return name != null && accountId != null && logoJson != null && industry != null
                && statsQuoteAsset != null && description != null && bannerJson != null
                && status != null;
    }
}
