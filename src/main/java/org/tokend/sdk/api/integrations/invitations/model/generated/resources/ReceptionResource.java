// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.invitations.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.*;
import org.tokend.sdk.api.integrations.invitations.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("receptions")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReceptionResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("receptionist")
    private BaseResource receptionist;
    
    public BaseResource getReceptionist() {
        return receptionist;
    }
}
