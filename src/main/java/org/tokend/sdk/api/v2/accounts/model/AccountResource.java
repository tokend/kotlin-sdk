package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.NameValue;
import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.kyc.model.KycResource;
import org.tokend.sdk.api.v2.signers.model.SignerResource;
import org.tokend.wallet.xdr.AccountType;

import java.util.List;

@Type("accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource extends BaseResource {

    @JsonProperty("is_blocked")
    private Boolean isBlocked;

    @JsonProperty("block_reasons_i")
    private Integer blockReasonsI;

    @JsonProperty("block_reasons")
    private List<? extends NameValue<Integer>> blockReasons;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_type_i")
    private Integer accountTypeI;

    @JsonProperty("thresholds")
    private Thresholds thresholds;

    @JsonProperty("policies")
    private Policies policies;

    @JsonProperty("limits")
    private Limits limits;

    @JsonProperty("deposit_accounts")
    private List<DepositAccount> depositAccounts;

    @Relationship("balances")
    private List<BalanceResource> balances;

    @Relationship("signers")
    private List<SignerResource> signers;

    @Relationship("kyc")
    private KycResource kyc;

    @Relationship("referrer")
    private AccountResource referrer;

    @Relationship("referrals")
    private List<AccountResource> referrals;

    public Boolean getBlocked() {
        return isBlocked;
    }

    public Integer getBlockReasonsI() {
        return blockReasonsI;
    }

    public List<? extends NameValue<Integer>> getBlockReasons() {
        return blockReasons;
    }

    public AccountType getAccountType() {
        for (AccountType type : AccountType.values()) {
            if (type.getValue() == accountTypeI) {
                return type;
            }
        }

        throw new IllegalArgumentException("No corresponding AccountType for " + accountTypeI);
    }

    public String getAccountTypeString() {
        return accountType;
    }

    public Integer getAccountTypeI() {
        return accountTypeI;
    }

    public Thresholds getThresholds() {
        return thresholds;
    }

    public Policies getPolicies() {
        return policies;
    }

    public Limits getLimits() {
        return limits;
    }

    public List<? extends DepositAccount> getDepositAccounts() {
        return depositAccounts;
    }

    public List<? extends BalanceResource> getBalances() {
        return balances;
    }

    public List<? extends SignerResource> getSigners() {
        return signers;
    }

    public KycResource getKyc() {
        return kyc;
    }

    public AccountResource getReferrer() {
        return referrer;
    }

    public List<? extends AccountResource> getReferrals() {
        return referrals;
    }

    @Override
    public boolean hasAttributes() {
        return isBlocked != null;
    }
}
