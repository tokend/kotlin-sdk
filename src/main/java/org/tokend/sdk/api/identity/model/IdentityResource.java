package org.tokend.sdk.api.identity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.BaseResource;

@Type("identity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityResource extends BaseResource {
    @JsonProperty("address")
    private String address;

    @JsonProperty("email")
    private String email;

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean hasAttributes() {
        return address != null && email != null;
    }
}
