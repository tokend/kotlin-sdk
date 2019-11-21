// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;


@Type("events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends BaseResource {

    @JsonProperty("details")
    private JsonNode details;

    public JsonNode getDetails() {
        return details;
    }

    @JsonProperty("end_time")
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("start_time")
    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    @Override
    public boolean isFilled() {
        return details != null &&
                endTime != null &&
                startTime != null
                ;
    }

    @Relationship("calendar")
    private BaseResource calendar;

    public BaseResource getCalendar() {
        return calendar;
    }

    @Relationship("holder")
    private BaseResource holder;

    public BaseResource getHolder() {
        return holder;
    }
}
