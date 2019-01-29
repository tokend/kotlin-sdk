package org.tokend.sdk.api.v2.history.model.effect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("effects-locked")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LockedEffectResource extends IssuedEffectResource { }
