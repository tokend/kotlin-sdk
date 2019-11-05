package org.tokend.sdk.api.identity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.sdk.api.identity.model.IdentityResource;

@Type("identity_settings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentitySettingsResource extends BaseResource {
    @JsonProperty("key")
    private String key;

    @JsonProperty("value")
    private JsonNode value;

    @Relationship("identity")
    private IdentityResource identity;

    public String getKey() {
        return key;
    }

    public JsonNode getValue() {
        return value;
    }

    public IdentityResource getIdentity() {
        return identity;
    }

    @Override
    public boolean isFilled() {
        return key != null && value != null;
    }
}
