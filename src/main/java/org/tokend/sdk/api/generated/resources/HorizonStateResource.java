// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("horizon-state")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HorizonStateResource extends BaseResource {
    
    @JsonProperty("core")
    private LedgerInfo core;
    
    public LedgerInfo getCore() {
        return core;
    }
    
    @JsonProperty("core_version")
    private String coreVersion;
    
    public String getCoreVersion() {
        return coreVersion;
    }
    
    @JsonProperty("current_time")
    private Date currentTime;
    
    public Date getCurrentTime() {
        return currentTime;
    }
    
    @JsonProperty("current_time_unix")
    private Long currentTimeUnix;
    
    public Long getCurrentTimeUnix() {
        return currentTimeUnix;
    }
    
    @JsonProperty("environment_name")
    private String environmentName;
    
    public String getEnvironmentName() {
        return environmentName;
    }
    
    @JsonProperty("history")
    private LedgerInfo history;
    
    public LedgerInfo getHistory() {
        return history;
    }
    
    @JsonProperty("history_v2")
    private LedgerInfo historyV2;
    
    public LedgerInfo getHistoryV2() {
        return historyV2;
    }
    
    @JsonProperty("master_account_id")
    private String masterAccountId;
    
    public String getMasterAccountId() {
        return masterAccountId;
    }
    
    @JsonProperty("network_passphrase")
    private String networkPassphrase;
    
    public String getNetworkPassphrase() {
        return networkPassphrase;
    }
    
    @JsonProperty("precision")
    private Long precision;
    
    public Long getPrecision() {
        return precision;
    }
    
    @JsonProperty("tx_expiration_period")
    private Long txExpirationPeriod;
    
    public Long getTxExpirationPeriod() {
        return txExpirationPeriod;
    }
    
    @JsonProperty("xdr_revision")
    private String xdrRevision;
    
    public String getXdrRevision() {
        return xdrRevision;
    }
    
    @Override
    public boolean isFilled() {
        return             core != null &&
            coreVersion != null &&
            currentTime != null &&
            currentTimeUnix != null &&
            environmentName != null &&
            history != null &&
            historyV2 != null &&
            masterAccountId != null &&
            networkPassphrase != null &&
            precision != null &&
            txExpirationPeriod != null &&
            xdrRevision != null 
        ;
    }
}
