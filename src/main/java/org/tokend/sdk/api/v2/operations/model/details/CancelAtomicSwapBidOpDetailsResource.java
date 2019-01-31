package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.base.UnknownResource;

@Type("operations-cancel-atomic-swap-bid")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelAtomicSwapBidOpDetailsResource extends OperationDetailsResource {
    // TODO: Find out type
    @Relationship("bid")
    private UnknownResource bid;

    public UnknownResource getBid() {
        return bid;
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }
}
