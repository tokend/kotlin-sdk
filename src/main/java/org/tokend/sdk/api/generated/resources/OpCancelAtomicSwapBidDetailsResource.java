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


@Type("operations-cancel-atomic-swap-ask")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCancelAtomicSwapBidDetailsResource extends OperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @Relationship("ask")
    private AtomicSwapAskResource ask;
    
    public AtomicSwapAskResource getAsk() {
        return ask;
    }
}
