package org.tokend.sdk.api.v2.base;

import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.BaseResource;

@Type("unknown")
public class UnknownResource extends BaseResource {
    @Override
    public boolean hasAttributes() {
        return false;
    }
}
