package org.tokend.sdk.api.v2.kyc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.BaseResource;

@Type("kyc")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KycResource extends BaseResource {

    @JsonProperty("first_name")
    private String firsName;

    @JsonProperty("last_name")
    private String lastName;

    public String getFirsName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean hasAttributes() {
        return firsName != null;
    }
}
