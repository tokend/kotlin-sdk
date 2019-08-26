package org.tokend.sdk.api.integrations.paymentproxy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("payment-account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentAccountResource extends BaseResource {

    @Override
    public boolean isFilled() {
        return true;
    }
}
