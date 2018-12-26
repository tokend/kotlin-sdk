package org.tokend.sdk.api.v2.sales.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.sales.model.SaleDetails;
import org.tokend.sdk.api.sales.model.SaleQuoteAsset;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.base.BaseResource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Type("sales")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleResource extends BaseResource {

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("soft_cap")
    private BigDecimal softCap;

    @JsonProperty("hard_cap")
    private BigDecimal hardCap;

    @JsonProperty("investors_count")
    private Integer investorsCount;

    @JsonProperty("type")
    private String type;

    @JsonProperty("type_i")
    private Integer typeI;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state_i")
    private Integer stateI;

    @JsonProperty("base_hard_cap")
    private BigDecimal baseHardCap;

    @JsonProperty("base_current_cap")
    private BigDecimal baseCurrentCap;

    @JsonProperty("current_cap")
    private BigDecimal currentCap;

    @JsonProperty("details")
    private SaleDetails details;

    @JsonProperty("sale_quote_assets")
    private List<? extends SaleQuoteAsset> SaleQuoteAssets;

    @JsonProperty("balances")
    private Map<String, String> balances;

    @Relationship("owner")
    private AccountResource owner;

    @Relationship("default_quote_asset")
    private AssetResource defaultQuoteAsset;

    @Relationship("base_asset")
    private AssetResource baseAsset;

    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public Integer getInvestorsCount() {
        return investorsCount;
    }

    public String getType() {
        return type;
    }

    public Integer getTypeI() {
        return typeI;
    }

    public String getState() {
        return state;
    }

    public Integer getStateI() {
        return stateI;
    }

    public BigDecimal getBaseHardCap() {
        return baseHardCap;
    }

    public BigDecimal getBaseCurrentCap() {
        return baseCurrentCap;
    }

    public BigDecimal getCurrentCap() {
        return currentCap;
    }

    public SaleDetails getDetails() {
        return details;
    }

    public List<? extends SaleQuoteAsset> getSaleQuoteAssets() {
        return SaleQuoteAssets;
    }

    public Map<String, String> getBalances() {
        return balances;
    }

    public AccountResource getOwner() {
        return owner;
    }

    public AssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }

    public AssetResource getBaseAsset() {
        return baseAsset;
    }

    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }

    @Override
    public boolean hasAttributes() {
        return startTime != null;
    }
}
