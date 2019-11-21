// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClosePollOp {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("poll_id")
    private Long pollId;
    
    public Long getPollId() {
        return pollId;
    }
    
    @JsonProperty("poll_result")
    private org.tokend.sdk.api.generated.inner.Enum pollResult;
    
    public org.tokend.sdk.api.generated.inner.Enum getPollResult() {
        return pollResult;
    }
}
