// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificDetails {
    
    @JsonProperty("capacity")
    private JsonNode capacity;
    
    public JsonNode getCapacity() {
        return capacity;
    }
    
    @JsonProperty("payloads")
    private List<String> payloads;
    
    public List<? extends String> getPayloads() {
        return payloads;
    }
    
    @JsonProperty("prices")
    private JsonNode prices;
    
    public JsonNode getPrices() {
        return prices;
    }
}
