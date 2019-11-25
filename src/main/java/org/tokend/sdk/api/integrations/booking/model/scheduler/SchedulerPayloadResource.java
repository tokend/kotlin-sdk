package org.tokend.sdk.api.integrations.booking.model.scheduler;

import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("payloads")
public class SchedulerPayloadResource extends BaseResource {
    @Override
    public boolean isFilled() {
        return true;
    }
}
