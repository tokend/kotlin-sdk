package org.tokend.sdk.api.base.model;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Links;

import org.jetbrains.annotations.Nullable;

/**
 * Represents base JSONAPI resource object
 */
public abstract class BaseResource {
    @Links
    private com.github.jasminb.jsonapi.Links links;

    @Id
    private String id;

    @Nullable
    public com.github.jasminb.jsonapi.Links getLinks() {
        return links;
    }

    public String getId() {
        return id;
    }

    /**
     * @return true if the resource object has resolved attributes,
     * false otherwise i.e. it's an unresolved relationship.
     * @see <a href="https://jsonapi.org/format/#document-resource-object-attributes">JSONAPI Attributes</a>
     * @see <a href="https://jsonapi.org/format/#document-resource-object-relationships">JSONAPI Relationships</a>
     */
    public abstract boolean hasAttributes();

    @Override
    public boolean equals(Object o) {
        return o instanceof BaseResource
                && ((BaseResource) o).id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
