package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("users-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResource extends BaseResource {
    private UserInfoResource() {
    }

    @JsonProperty("avatar")
    private JsonNode avatar;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public JsonNode getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isFilled() {
        return avatar != null && firstName != null && lastName != null;
    }
}
