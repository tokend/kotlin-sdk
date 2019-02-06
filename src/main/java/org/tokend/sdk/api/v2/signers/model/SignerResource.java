package org.tokend.sdk.api.v2.signers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.NameValue;
import org.tokend.sdk.api.base.model.BaseResource;

import java.util.List;

@Type("signers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerResource extends BaseResource {

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("type_i")
    private Integer typeI;

    @JsonProperty("types")
    private List<? extends NameValue<Integer>> types;

    @JsonProperty("identity")
    private Integer identity;

    @JsonProperty("name")
    private String name;

    public Integer getWeight() {
        return weight;
    }

    public Integer getTypeI() {
        return typeI;
    }

    public List<? extends NameValue<Integer>> getTypes() {
        return types;
    }

    public Integer getIdentity() {
        return identity;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean hasAttributes() {
        return weight != null;
    }
}
