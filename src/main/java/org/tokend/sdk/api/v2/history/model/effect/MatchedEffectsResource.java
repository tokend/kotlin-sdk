package org.tokend.sdk.api.v2.history.model.effect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import java.math.BigDecimal;

@Type("effects-matched")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchedEffectsResource extends EffectResource {

    @JsonProperty("offer_id")
    private Long offerId;

    @JsonProperty("order_book_id")
    private Long orderBookId;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("charged")
    private RenameMeEntity charged;

    @JsonProperty("funded")
    private RenameMeEntity funded;

    @Override
    public boolean hasAttributes() {
        return offerId != null;
    }
}
