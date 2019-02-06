package org.tokend.sdk.api.v2.transactions.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.wallet.xdr.MemoType;

import java.util.Date;
import java.util.List;

@Type("transactions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResource extends BaseResource {

    @JsonProperty("paging_token")
    private String pagingToken;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("ledger")
    private Integer ledger;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("source_account")
    private String sourceAccount;

    @JsonProperty("fee_paid")
    private Long feePaid;

    @JsonProperty("operation_count")
    private Integer operationCount;

    @JsonProperty("envelope_xdr")
    private String envelopeXdr;

    @JsonProperty("result_xdr")
    private String resultXdr;

    @JsonProperty("result_meta_xdr")
    private String resultMetaXdr;

    @JsonProperty("fee_meta_xdr")
    private String feeMetaXdr;

    @JsonProperty("memo_type")
    private String memoType;

    @JsonProperty("signatures")
    private List<String> signatures;

    @JsonProperty("valid_after")
    private Date validAfter;

    @JsonProperty("valid_before")
    private Date validBefore;

    public String getPagingToken() {
        return pagingToken;
    }

    public String getHash() {
        return hash;
    }

    public Integer getLedger() {
        return ledger;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public Long getFeePaid() {
        return feePaid;
    }

    public Integer getOperationCount() {
        return operationCount;
    }

    public String getEnvelopeXdr() {
        return envelopeXdr;
    }

    public String getResultXdr() {
        return resultXdr;
    }

    public String getResultMetaXdr() {
        return resultMetaXdr;
    }

    public String getFeeMetaXdr() {
        return feeMetaXdr;
    }

    public String getMemoTypeString() {
        return memoType;
    }

    public MemoType getMemoType() {
        String type = ("memo_" + memoType).toUpperCase();
        return MemoType.valueOf(type);
    }

    public List<String> getSignatures() {
        return signatures;
    }

    public Date getValidAfter() {
        return validAfter;
    }

    public Date getValidBefore() {
        return validBefore;
    }

    @Override
    public boolean hasAttributes() {
        return pagingToken != null;
    }
}
