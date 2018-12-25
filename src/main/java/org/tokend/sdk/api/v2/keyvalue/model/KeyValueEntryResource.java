package org.tokend.sdk.api.v2.keyvalue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.wallet.xdr.KeyValueEntryType;

import java.util.Map;

@Type("key_value_entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyValueEntryResource extends BaseResource {
    @JsonProperty("value_type")
    private String valueTypeString;

    @JsonProperty("value_type_i")
    private int valueTypeI;

    @JsonProperty("value")
    private Map<String, String> values;

    public KeyValueEntryType getValueType() {
        return KeyValueEntryType.valueOf(valueTypeString.toUpperCase());
    }

    public int getValueTypeI() {
        return valueTypeI;
    }

    public String getValue() {
        return values.get(valueTypeString.toLowerCase());
    }

    @Override
    public boolean hasAttributes() {
        return valueTypeString != null;
    }
}
