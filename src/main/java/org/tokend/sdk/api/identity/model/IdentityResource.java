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

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("passport")
    private String passportId;

    @JsonProperty("telegram_username")
    private String telegramUsername;

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassportId() {
        return passportId;
    }

    public String getTelegramUsername() {
        return telegramUsername;
    }

    @Override
    public boolean isFilled() {
        return address != null && email != null && phoneNumber != null && passportId != null
                && telegramUsername != null;
    }
}
