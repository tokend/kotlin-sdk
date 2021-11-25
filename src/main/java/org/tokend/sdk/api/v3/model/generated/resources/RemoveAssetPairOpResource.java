// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-remove-asset-pair")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveAssetPairOpResource extends BaseOperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @JsonIgnore
    @Relationship("base")
    private AssetResource base;
    
    public AssetResource getBase() {
        return base;
    }
    
    @JsonIgnore
    @Relationship("quote")
    private AssetResource quote;
    
    public AssetResource getQuote() {
        return quote;
    }
}
