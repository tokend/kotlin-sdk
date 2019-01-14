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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Type("sales")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleResource extends BaseResource {

    @JsonProperty("start_time")
    private Date startDate;

    @JsonProperty("end_time")
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
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

    public boolean isAvailable() {
        return !isUpcoming() && !isEnded();
    }

    public boolean isUpcoming() {
        return startDate.after(new Date());
    }

    public boolean isEnded() {
        return isClosed() || isCanceled();
    }

    public boolean isOpen() {
        return stateI == STATE_OPEN;
    }

    public boolean isClosed() {
        return stateI == STATE_CLOSED;
    }

    public boolean isCanceled() {
        return stateI == STATE_CANCELED;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SaleResource
                && ((SaleResource) o).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public boolean hasAttributes() {
        return startDate != null;
    }

    public static int STATE_OPEN = 1;
    public static int STATE_CLOSED = 2;
    public static int STATE_CANCELED = 4;
}
