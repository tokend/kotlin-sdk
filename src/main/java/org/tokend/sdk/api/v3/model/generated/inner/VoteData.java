// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoteData {
    
    @JsonProperty("creation_time")
    @Nullable
    private Date creationTime;
    
    @Nullable
    public Date getCreationTime() {
        return creationTime;
    }
    
    @JsonProperty("custom_choice")
    @Nullable
    private String customChoice;
    
    @Nullable
    public String getCustomChoice() {
        return customChoice;
    }
    
    @JsonProperty("poll_type")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum pollType;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getPollType() {
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
