package org.tokend.sdk.api.v2.requests.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.requests.model.details.ReviewableRequestDetailsResource;

import java.util.Date;

@Type("requests")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewableRequestResource extends BaseResource {
    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state_i")
    private Integer typeI;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("reject_reason")
    private String rejectReason;

    @JsonProperty("reference")
    private String reference;

    @Relationship("requestor")
    private AccountResource requestor;

    @Relationship("reviewer")
    private AccountResource reviewer;

    @Relationship("request_details")
    private ReviewableRequestDetailsResource details;

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getState() {
        return state;
    }

    public Integer getTypeI() {
        return typeI;
    }

    public String getHash() {
        return hash;
    }

    @Nullable
    public String getRejectReason() {
        return (rejectReason != null && !rejectReason.isEmpty())
                ? rejectReason
                : null;
    }

    public String getReference() {
        return reference;
    }

    public AccountResource getRequestor() {
        return requestor;
    }

    public AccountResource getReviewer() {
        return reviewer;
    }

    public <T extends ReviewableRequestDetailsResource> T getDetails() {
        return (T) details;
    }

    @Override
    public boolean hasAttributes() {
        return createdAt != null;
    }
}
