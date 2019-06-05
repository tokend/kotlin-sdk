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
    private XdrEnumValue pollType;
    
    public XdrEnumValue getPollType() {
        return pollType;
    }
    
    @JsonProperty("single_choice")
    @Nullable
    private Long singleChoice;
    
    @Nullable
    public Long getSingleChoice() {
        return singleChoice;
    }
}
