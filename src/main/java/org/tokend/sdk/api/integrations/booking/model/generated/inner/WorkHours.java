// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkHours {
    
    @JsonProperty("end")
    private Time end;
    
    public Time getEnd() {
        return end;
    }
    
    @JsonProperty("start")
    private Time start;
    
    public Time getStart() {
        return start;
    }
}
