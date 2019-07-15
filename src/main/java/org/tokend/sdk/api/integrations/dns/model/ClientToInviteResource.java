package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("clients")
public class ClientToInviteResource {

    public ClientToInviteResource(String email) {
        this.email = email;
    }

    public ClientToInviteResource(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Id
    private String id;

    public String getId() {
        return id;
    }

    @JsonProperty
    private String email;

    public String getEmail() {
        return email;
    }
}
