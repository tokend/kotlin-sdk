package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.tokend.sdk.api.base.model.NameValue;

import java.util.List;

public class Policies {

    @JsonProperty("type_i")
    private Integer typeI;

    @JsonProperty("types")
    private List<? extends NameValue<Integer>> types;

    public Integer getTypeI() {
        return typeI;
    }

    public List<? extends NameValue<Integer>> getTypes() {
        return types;
    }
}
