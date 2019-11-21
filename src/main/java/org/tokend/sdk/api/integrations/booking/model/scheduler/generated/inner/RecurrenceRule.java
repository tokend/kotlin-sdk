// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.inner;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecurrenceRule {
    
    @JsonProperty("by_hours")
    private List<Integer> byHours;
    
    public List<? extends Integer> getByHours() {
        return byHours;
    }
    
    @JsonProperty("by_minutes")
    private List<Integer> byMinutes;
    
    public List<? extends Integer> getByMinutes() {
        return byMinutes;
    }
    
    @JsonProperty("by_month_days")
    private List<Integer> byMonthDays;
    
    public List<? extends Integer> getByMonthDays() {
        return byMonthDays;
    }
    
    @JsonProperty("by_months")
    private List<Integer> byMonths;
    
    public List<? extends Integer> getByMonths() {
        return byMonths;
    }
    
    @JsonProperty("by_seconds")
    private List<Integer> bySeconds;
    
    public List<? extends Integer> getBySeconds() {
        return bySeconds;
    }
    
    @JsonProperty("by_set_pos")
    private List<Integer> bySetPos;
    
    public List<? extends Integer> getBySetPos() {
        return bySetPos;
    }
    
    @JsonProperty("by_week_numbers")
    private List<Integer> byWeekNumbers;
    
    public List<? extends Integer> getByWeekNumbers() {
        return byWeekNumbers;
    }
    
    @JsonProperty("by_weekdays")
    private List<QualifiedWeekday> byWeekdays;
    
    public List<? extends QualifiedWeekday> getByWeekdays() {
        return byWeekdays;
    }
    
    @JsonProperty("by_year_days")
    private List<Integer> byYearDays;
    
    public List<? extends Integer> getByYearDays() {
        return byYearDays;
    }
    
    @JsonProperty("count")
    @Nullable
    private Long count;
    
    @Nullable
    public Long getCount() {
        return count;
    }
    
    @JsonProperty("dtstart")
    private String dtstart;
    
    public String getDtstart() {
        return dtstart;
    }
    
    @JsonProperty("frequency")
    private Integer frequency;
    
    public Integer getFrequency() {
        return frequency;
    }
    
    @JsonProperty("interval")
    @Nullable
    private Integer interval;
    
    @Nullable
    public Integer getInterval() {
        return interval;
    }
    
    @JsonProperty("until")
    @Nullable
    private String until;
    
    @Nullable
    public String getUntil() {
        return until;
    }
    
    @JsonProperty("until_floating")
    @Nullable
    private Boolean untilFloating;
    
    @Nullable
    public Boolean untilFloating() {
        return untilFloating;
    }
}
