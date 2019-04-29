// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("order-books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("quote_asset")
    private AssetResource quoteAsset;
    
    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }
    
    @Relationship("buy_entries")
    private List<OrderBookEntryResource> buyEntries;
    
    public List<? extends OrderBookEntryResource> getBuyEntries() {
        return buyEntries;
    }
    
    @Relationship("sell_entries")
    private List<OrderBookEntryResource> sellEntries;
    
    public List<? extends OrderBookEntryResource> getSellEntries() {
        return sellEntries;
    }
}
