package org.tokend.sdk.api.v2.base;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("unknown")
public class UnknownResource extends BaseResource {
    @Override
    public boolean hasAttributes() {
        return false;
    }
}
