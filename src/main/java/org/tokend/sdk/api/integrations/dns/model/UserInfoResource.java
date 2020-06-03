package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.sdk.api.base.model.RemoteFile;

import java.util.Map;

@Type("users-info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResource extends BaseResource {
    private UserInfoResource() {
    }

    @JsonProperty("documents")
    private Map<String, RemoteFile> documents;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Map<String, RemoteFile> getDocuments() {
        return documents;
    }

    @Override
    public boolean isFilled() {
        return documents != null && firstName != null && lastName != null;
    }
}
