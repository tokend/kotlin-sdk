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
public class VoteData {
    
    @JsonProperty("poll_type")
    private Enum pollType;
    
    public Enum getPollType() {
        return pollType;
    }
    
    @JsonProperty("single_choice")
    private Long singleChoice;
    
    public Long getSingleChoice() {
        return singleChoice;
    }
}
