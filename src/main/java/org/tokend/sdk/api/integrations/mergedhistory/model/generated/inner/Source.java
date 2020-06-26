// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.mergedhistory.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    
    @JsonProperty("body")
    private JsonNode body;
    
    public JsonNode getBody() {
        return body;
    }
    
    @JsonProperty("dimensions")
    private JsonNode dimensions;
    
    public JsonNode getDimensions() {
        return dimensions;
    }
    
    @JsonProperty("id_path")
    private String idPath;
    
    public String getIdPath() {
        return idPath;
    }
    
    @JsonProperty("includes")
    private List<String> includes;
    
    public List<? extends String> getIncludes() {
        return includes;
    }
    
    @JsonProperty("participants")
    private List<String> participants;
    
    public List<? extends String> getParticipants() {
        return participants;
    }
    
    @JsonProperty("url")
    private String url;
    
    public String getUrl() {
        return url;
    }
}
