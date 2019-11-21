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


@Type("operations-remove-asset-pair")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveAssetPairOpResource extends BaseOperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @Relationship("base")
    private AssetResource base;
    
    public AssetResource getBase() {
        return base;
    }
    
    @Relationship("quote")
    private AssetResource quote;
    
    public AssetResource getQuote() {
        return quote;
    }
}
