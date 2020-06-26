// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources.*;
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("merge-histories-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoResource extends BaseResource {
    
    @JsonProperty("sources")
    private List<Source> sources;
    
    public List<? extends Source> getSources() {
        return sources;
    }
    
    @Override
    public boolean isFilled() {
        return             sources != null 
        ;
    }
}
